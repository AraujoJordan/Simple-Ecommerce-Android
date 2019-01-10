package com.araujo.jordan.shopifymobilesummerchallenge2019.contract

import android.content.Context
import com.araujo.jordan.shopifymobilesummerchallenge2019.api.ShopifyAPIInteractor


/**
 * BeerListPresenter Class
 * Parcelable for easily pass the Beer thought Activities
 * Created by araujojordan on 08/01/2019
 */

open class ProductListPresenter : ProductListContract.Presenter {


    private lateinit var shopifyAPIInteractor: ShopifyAPIInteractor
    lateinit var view: ProductListContract.View

    override fun resume(ctx: Context) {
        shopifyAPIInteractor = ShopifyAPIInteractor(ctx)
        shopifyAPIInteractor.attachProdView(view)
    }

    override fun attach(view: ProductListContract.View) {
        this.view = view
    }

    override fun fetchProdData(page: Int, prodIdsArr: String) {
        shopifyAPIInteractor.fetchProducts(page,prodIdsArr)
    }

}
