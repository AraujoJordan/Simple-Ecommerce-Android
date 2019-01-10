package com.araujo.jordan.shopifymobilesummerchallenge2019.api

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import com.araujo.jordan.shopifymobilesummerchallenge2019.contract.CollectionListContract
import com.araujo.jordan.shopifymobilesummerchallenge2019.contract.CustomListContract
import com.araujo.jordan.shopifymobilesummerchallenge2019.contract.ProductListContract
import com.araujo.jordan.shopifymobilesummerchallenge2019.model.CollectionArray
import com.araujo.jordan.shopifymobilesummerchallenge2019.model.CustomCollectionArray
import com.araujo.jordan.shopifymobilesummerchallenge2019.model.ProductArray
import okhttp3.Cache
import okhttp3.OkHttpClient
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
class ShopifyAPIInteractor(ctx: Context) {

    private val BASE_URL = "https://shopicruit.myshopify.com/admin/"
    private var okHttpClient: OkHttpClient
    private var retrofit: Retrofit
    private var customColAPI: CustomCollectionService
    private var colAPI: CollectionService
    private var prodAPI: ProductService

    private lateinit var ccView: CustomListContract.View
    private lateinit var colView: CollectionListContract.View
    private lateinit var prodView: ProductListContract.View

    init {
        okHttpClient = OkHttpClient.Builder()
            .cache(Cache(ctx.cacheDir, 5 * 1024 * 1024))
            .addInterceptor { chain ->
                var request = chain.request()
                request = if (hasNetwork(ctx)!!)
                    request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
                else
                    request.newBuilder().header(
                        "Cache-Control",
                        "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 2
                    ).build()
                chain.proceed(request)
            }
            .build()
        retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        customColAPI = retrofit.create(CustomCollectionService::class.java)
        colAPI = retrofit.create(CollectionService::class.java)
        prodAPI = retrofit.create(ProductService::class.java)
    }

    fun attachCustomColView(view: CustomListContract.View) {
        this.ccView = view
    }

    fun attachColView(view: CollectionListContract.View) {
        this.colView = view
    }

    fun attachProdView(view: ProductListContract.View) {
        this.prodView = view
    }


    /**
     * Request Custom Collections with pagination
     */
    fun fetchCustomCollections(page: Int) {
        Log.d("REQUEST", "fetchCustomCollections()")
        ccView.showLoading(true)
        customColAPI.fetchCol(page).enqueue(object : Callback<CustomCollectionArray> {
            override fun onFailure(call: Call<CustomCollectionArray>, t: Throwable) {
                ccView.showLoading(false)
                Log.e("CCInteractor", "fetchCC() onFailure() " + t.message)
                ccView.showErrorMessage("Error to retrieve Custom Collection")
            }

            override fun onResponse(call: Call<CustomCollectionArray>, response: Response<CustomCollectionArray>) {
                if (response.isSuccessful) {
                    Log.d("CCInteractor", "fetchCC() onResponse() ")
                    val colArrList = response.body()
                    ccView.showLoading(false)
                    ccView.fetchCustColSuccess(colArrList?.custom_collections)
                } else {
                    ccView.showLoading(false)
                    ccView.showErrorMessage("Error to retrieve Custom Collection")
                    Log.e("CCInteractor", "fetchCC() onResponse() " + response.raw())
                }
            }
        })
    }

    /**
     * Fetch Collections list from the given Collection ID
     */
    fun fetchCollections(page: Int, colId: String) {
        Log.d("REQUEST", "fetchCollections()")
        colAPI.fetchColProd(page, colId).enqueue(object : Callback<CollectionArray> {
            override fun onFailure(call: Call<CollectionArray>, t: Throwable) {
                Log.e("CInteractor", "fetchCol() onFailure() " + t.message)
                colView.showErrorMessage("Error to retrieve Collection")
                colView.showLoading(false)
            }

            override fun onResponse(call: Call<CollectionArray>, response: Response<CollectionArray>) {
                if (response.isSuccessful) {
                    Log.d("CInteractor", "fetchCol() onResponse()")
                    val colArrList = response.body()
                    colView.fetchColSuccess(colArrList?.collects)
                } else {
                    colView.showErrorMessage("Error to retrieve Collection")
                    Log.e("CInteractor", "fetchCol() onResponse() " + response.raw())
                    colView.showLoading(false)
                }
            }
        })
    }

    /**
     * Fetch Products list from the given products ID
     */
    fun fetchProducts(page: Int, prodIdsArr: String) {
        Log.d("REQUEST", "fetchProducts()")
        prodAPI.fetchProd(page, prodIdsArr).enqueue(object : Callback<ProductArray> {
            override fun onFailure(call: Call<ProductArray>, t: Throwable) {
                Log.e("PInteractor", "fetchCol() onFailure() " + t.message)
                prodView.showErrorMessage("Error to retrieve Collection")
                prodView.showLoading(false)
            }

            override fun onResponse(call: Call<ProductArray>, response: Response<ProductArray>) {
                if (response.isSuccessful) {
                    Log.d("PInteractor", "fetchCol() onResponse()")
                    val colArrList = response.body()
                    prodView.fetchProductsSuccess(colArrList?.products)
                } else {
                    prodView.showErrorMessage("Error to retrieve Collection")
                    Log.e("PInteractor", "fetchCol() onResponse() " + response.raw())
                    prodView.showLoading(false)
                }
            }
        })
    }

    fun hasNetwork(context: Context): Boolean? {
        var isConnected: Boolean? = false // Initial Value
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        if (activeNetwork != null && activeNetwork.isConnected)
            isConnected = true
        return isConnected
    }


}
