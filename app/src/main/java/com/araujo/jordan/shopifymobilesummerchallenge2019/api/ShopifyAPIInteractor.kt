package com.araujo.jordan.shopifymobilesummerchallenge2019.api

import android.util.Log
import com.araujo.jordan.shopifymobilesummerchallenge2019.contract.CollectionListContract
import com.araujo.jordan.shopifymobilesummerchallenge2019.contract.CustomListContract
import com.araujo.jordan.shopifymobilesummerchallenge2019.model.CollectionArray
import com.araujo.jordan.shopifymobilesummerchallenge2019.model.CustomCollectionArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * ShopifyAPIInteractor
 * API request manager that resolves the Retrofit2 usage
 * Created by araujojordan on 08/01/2019.
 */
class ShopifyAPIInteractor {

    private val BASE_URL = "https://shopicruit.myshopify.com/admin/"
    private val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val customColAPI = retrofit.create(CustomCollectionService::class.java)
    private val colAPI = retrofit.create(CollectionService::class.java)

    private lateinit var ccView: CustomListContract.View
    private lateinit var colView: CollectionListContract.View

    fun attachCustomColView(view: CustomListContract.View) {
        this.ccView = view
    }

    fun attachColView(view: CollectionListContract.View) {
        this.colView = view
    }


    /**
     * Request Custom Collections with pagination
     */
    fun fetchCustomCollections(page: Int) {
        customColAPI.fetchCol(page).enqueue(object : Callback<CustomCollectionArray> {
            override fun onFailure(call: Call<CustomCollectionArray>, t: Throwable) {
                Log.e("CCInteractor", "fetchCol() onFailure() " + t.message)
                ccView.showErrorMessage("Error to retrieve Custom Collection")
            }

            override fun onResponse(call: Call<CustomCollectionArray>, response: Response<CustomCollectionArray>) {
                if (response.isSuccessful) {
                    val colArrList = response.body()
                    ccView.fetchCustColSuccess(colArrList?.custom_collections)
                } else {
                    ccView.showErrorMessage("Error to retrieve Custom Collection")
                    Log.e("CCInteractor", "fetchCCol() onResponse() " + response.raw())
                }
            }
        })
    }

    /**
     * Fetch Collections products list from the given Collection ID
     */
    fun fetchCollections(page: Int, colId: Int) {
        colAPI.fetchColProd(page, colId).enqueue(object : Callback<CollectionArray> {
            override fun onFailure(call: Call<CollectionArray>, t: Throwable) {
                Log.e("CInteractor", "fetchCol() onFailure() " + t.message)
                colView.showErrorMessage("Error to retrieve Collection")
            }

            override fun onResponse(call: Call<CollectionArray>, response: Response<CollectionArray>) {
                if (response.isSuccessful) {
                    val colArrList = response.body()
                    colView.fetchColSuccess(colArrList?.collects)
                } else {
                    colView.showErrorMessage("Error to retrieve Collection")
                    Log.e("CInteractor", "fetchCol() onResponse() " + response.raw())
                }
            }
        })
    }
}
