package com.araujo.jordan.shopifymobilesummerchallenge2019.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.araujo.jordan.shopifymobilesummerchallenge2019.R
import com.araujo.jordan.shopifymobilesummerchallenge2019.contract.CollectionListPresenter
import com.araujo.jordan.shopifymobilesummerchallenge2019.contract.CustomListContract
import com.araujo.jordan.shopifymobilesummerchallenge2019.model.CustomCollection
import androidx.recyclerview.widget.LinearLayoutManager
import com.ethanhua.skeleton.Skeleton


class CustomCollectionAdapter :
    RecyclerView.Adapter<CustomCollectionAdapter.ViewHolder>(),
    CustomListContract.View{

    private var itemList: List<CustomCollection> = mutableListOf()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val customitem_title = view.findViewById(R.id.customitem_title) as TextView
        val customitem_subtitle = view.findViewById(R.id.customitem_subtitle) as TextView
        val customitem_recycleview = view.findViewById(R.id.customitem_recycleview) as RecyclerView
    }

    fun attachList(itemList: List<CustomCollection>) {
        this.itemList = itemList
        notifyDataSetChanged()
    }


    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomCollectionAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.listitem_customcollection, parent, false) as View
        )
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun String.capitalizeWords(): String = split(" ").map { it.capitalize() }.joinToString(" ")

    override fun onBindViewHolder(holder: CustomCollectionAdapter.ViewHolder, position: Int) {
        val col = itemList[position]
        holder.customitem_title.text = col.title.capitalizeWords()
        holder.customitem_subtitle.text = col.body_html
        if (col.body_html == "") {
            holder.customitem_subtitle.visibility = View.GONE
        } else
            holder.customitem_subtitle.visibility = View.VISIBLE

        val layoutManager = LinearLayoutManager(holder.customitem_recycleview.context,
            LinearLayoutManager.HORIZONTAL, false)

        var adapter = CollectionAdapter(
            holder.customitem_recycleview.context,
            col.id,
            holder.customitem_recycleview
        )
        holder.customitem_recycleview.adapter = adapter
        holder.customitem_recycleview.layoutManager = layoutManager
        adapter.showLoading(true)
    }

    override fun showLoading(isLoading: Boolean) {

    }

    override fun showErrorMessage(error: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun fetchCustColSuccess(list: List<CustomCollection>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}