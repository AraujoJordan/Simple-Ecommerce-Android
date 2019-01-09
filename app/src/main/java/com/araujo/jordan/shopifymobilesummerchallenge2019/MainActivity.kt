package com.araujo.jordan.shopifymobilesummerchallenge2019

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.araujo.jordan.shopifymobilesummerchallenge2019.adapter.CustomCollectionAdapter
import com.araujo.jordan.shopifymobilesummerchallenge2019.contract.CustomListContract
import com.araujo.jordan.shopifymobilesummerchallenge2019.contract.CustomListPresenter
import com.araujo.jordan.shopifymobilesummerchallenge2019.model.CustomCollection
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() ,CustomListContract.View {

    private lateinit var customListPresenter : CustomListPresenter
    private lateinit var adapter: CustomCollectionAdapter

    override fun showLoading(isLoading: Boolean) {

    }

    override fun showErrorMessage(error: String) {

    }

    override fun fetchCustColSuccess(list: List<CustomCollection>?) {
        Log.v("fetchOK","fetchIsOver with "+list!!.size)
        adapter = CustomCollectionAdapter(list!!.toMutableList(),)
        main_recycleview.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        customListPresenter.resume()
        customListPresenter.fetchData(1)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        customListPresenter = CustomListPresenter()
        customListPresenter.attach(this)
    }
}
