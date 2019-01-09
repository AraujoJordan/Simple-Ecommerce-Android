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

class CustomCollectionAdapter(
    private val itemList: MutableList<CustomCollection>,
    private var colPresenter: CollectionListPresenter
) :
    RecyclerView.Adapter<CustomCollectionAdapter.ViewHolder>(),
    CustomListContract.View{

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val customitem_title = view.findViewById(R.id.customitem_title) as TextView
        val customitem_subtitle = view.findViewById(R.id.customitem_subtitle) as TextView

        val card1 = view.findViewById(R.id.card1) as CardView

        val titleCard1 = card1.findViewById(R.id.card_title) as TextView
        val imageCard1 = card1.findViewById(R.id.card_image) as ImageView
        val priceCard1 = card1.findViewById(R.id.card_price) as TextView

        val card2 = view.findViewById(R.id.card2) as CardView

        val titleCard2 = card2.findViewById(R.id.card_title) as TextView
        val imageCard2 = card2.findViewById(R.id.card_image) as ImageView
        val priceCard2 = card2.findViewById(R.id.card_price) as TextView

        val card3 = view.findViewById(R.id.card3) as CardView

        val titleCard3 = card3.findViewById(R.id.card_title) as TextView
        val imageCard3 = card3.findViewById(R.id.card_image) as ImageView
        val priceCard3 = card3.findViewById(R.id.card_price) as TextView
    }

    init {
        this.colPresenter = CollectionListPresenter()
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

    override fun onBindViewHolder(holder: CustomCollectionAdapter.ViewHolder, position: Int) {
        val col = itemList[position]
        holder.customitem_title.text = col.title
        holder.customitem_subtitle.text = col.body_html
        if (col.body_html == "") {
            holder.customitem_subtitle.visibility = View.GONE
        } else
            holder.customitem_subtitle.visibility = View.VISIBLE

        colPresenter.fetchColData(1,col.id)


    }

    override fun showLoading(isLoading: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showErrorMessage(error: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun fetchCustColSuccess(list: List<CustomCollection>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}