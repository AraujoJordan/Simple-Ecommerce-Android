package com.araujo.jordan.shopifymobilesummerchallenge2019.adapter


import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.araujo.jordan.shopifymobilesummerchallenge2019.R
import com.araujo.jordan.shopifymobilesummerchallenge2019.contract.CollectionListContract
import com.araujo.jordan.shopifymobilesummerchallenge2019.contract.CollectionListPresenter
import com.araujo.jordan.shopifymobilesummerchallenge2019.contract.ProductListContract
import com.araujo.jordan.shopifymobilesummerchallenge2019.contract.ProductListPresenter
import com.araujo.jordan.shopifymobilesummerchallenge2019.model.Collection
import com.araujo.jordan.shopifymobilesummerchallenge2019.model.Product
import com.ethanhua.skeleton.RecyclerViewSkeletonScreen
import com.ethanhua.skeleton.Skeleton
import com.squareup.picasso.Picasso
import java.text.NumberFormat

class CollectionAdapter(ctx: Context, colID: String, recView: RecyclerView) :
    RecyclerView.Adapter<CollectionAdapter.ViewHolder>(),
    CollectionListContract.View, ProductListContract.View {

    private var colId: String = colID
    private var itemList = mutableListOf<Product>()
    private var colPresenter = CollectionListPresenter()
    private var prodPresenter = ProductListPresenter()
    private var recView: RecyclerView = recView

    private var skeletonCards: RecyclerViewSkeletonScreen? = null //fancy loading

    private val picasso = Picasso.get()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val card_title = view.findViewById(R.id.card_title) as TextView
        val card_price = view.findViewById(R.id.card_price) as TextView
        val card_image = view.findViewById(R.id.card_image) as ImageView
    }

    init {
        this.colPresenter.attach(this)
        this.colPresenter.resume(ctx)
        this.prodPresenter.attach(this)
        this.prodPresenter.resume(ctx)
        this.colPresenter.fetchColData(1, colId)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.card_customcollection, parent, false) as View
        )
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: CollectionAdapter.ViewHolder, position: Int) {
        val prod = itemList[position]

        holder.card_title.text = prod.title
        Log.d("REQUEST", "fetchImage()")
        picasso
            .load(prod.image.src)
            .into(holder.card_image)

        var maxPrice = 0f
        var minPrice = 0f
        for (variant in prod.variants) {
            val price = variant.price.toFloat()
            if (maxPrice == 0f && minPrice == 0f) {
                maxPrice = price
                minPrice = price
            }
            if (maxPrice < price)
                maxPrice = price
            if (minPrice > price)
                minPrice = price
        }

        var formatter = NumberFormat.getCurrencyInstance()

        if (maxPrice > minPrice)
            holder.card_price.text = formatter.format(minPrice) + " ~ " + formatter.format(maxPrice)
        else
            holder.card_price.text = formatter.format(maxPrice)

    }

    override fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            skeletonCards = Skeleton.bind(recView)
                .adapter(this)
                .load(R.layout.card_customcollection_skeleton)
                .shimmer(false)
                .show()
        } else {
            skeletonCards?.hide()
        }
    }

    override fun showErrorMessage(error: String) {
    }

    override fun fetchColSuccess(list: List<Collection>?) {
        var ids = ""
        val maxSize = if (list!!.size >= 3) 2 else list.size - 1

        for (i in 0..maxSize) {
            ids += list[i].product_id
            if (i <= maxSize - 1)
                ids += ","
        }

        prodPresenter.fetchProdData(1, ids)
    }

    override fun fetchProductsSuccess(list: List<Product>?) {
        itemList.addAll(list!!)
        showLoading(false)
        notifyDataSetChanged()
        Log.d("fetchProductsSuccess()", " items on list " + itemList.size)
    }

}