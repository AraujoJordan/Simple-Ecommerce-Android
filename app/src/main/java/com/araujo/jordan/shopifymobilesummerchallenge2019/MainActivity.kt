package com.araujo.jordan.shopifymobilesummerchallenge2019

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.araujo.jordan.shopifymobilesummerchallenge2019.adapter.CustomCollectionAdapter
import com.araujo.jordan.shopifymobilesummerchallenge2019.contract.CustomListContract
import com.araujo.jordan.shopifymobilesummerchallenge2019.contract.CustomListPresenter
import com.araujo.jordan.shopifymobilesummerchallenge2019.model.CustomCollection
import com.ethanhua.skeleton.RecyclerViewSkeletonScreen
import com.ethanhua.skeleton.Skeleton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() ,CustomListContract.View {

    private lateinit var customListPresenter : CustomListPresenter
    private lateinit var adapter: CustomCollectionAdapter
    private var skeletonCC: RecyclerViewSkeletonScreen? = null //fancy loading

    override fun showLoading(isLoading: Boolean) {
        if (isLoading && adapter!=null) {
            skeletonCC = Skeleton.bind(main_recycleview)
                .adapter(adapter)
                .load(R.layout.listitem_customcollection_skeleton)
                .color(R.color.softGrey)
                .shimmer(false)
                .show()
        } else {
            skeletonCC?.hide()
        }
    }

    override fun showErrorMessage(error: String) {

    }

    override fun fetchCustColSuccess(list: List<CustomCollection>?) {
        Log.d("fetchOK","fetchIsOver with "+list!!.size)
        adapter.attachList(list)

    }

    override fun onResume() {
        super.onResume()
        customListPresenter.resume(this)

        Log.d("fetch","start fetch CC ")
        customListPresenter.fetchCCData(1)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = CustomCollectionAdapter()
        main_recycleview.adapter = adapter

        customListPresenter = CustomListPresenter()
        customListPresenter.attach(this)

    }
}
