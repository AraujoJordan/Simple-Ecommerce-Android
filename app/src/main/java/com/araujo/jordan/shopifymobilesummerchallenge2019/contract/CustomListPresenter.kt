package com.araujo.jordan.shopifymobilesummerchallenge2019.contract

import android.content.Context
import com.araujo.jordan.shopifymobilesummerchallenge2019.api.ShopifyAPIInteractor


/**
 * BeerListPresenter Class
 * Parcelable for easily pass the Beer thought Activities
 * Created by araujojordan on 08/01/2019
 */

open class CustomListPresenter : CustomListContract.Presenter {
    override fun fetchCCData(page: Int) {
        view.showLoading(true)
        shopifyAPIInteractor.fetchCustomCollections(page)
    }

    private lateinit var shopifyAPIInteractor: ShopifyAPIInteractor
    lateinit var view: CustomListContract.View

    override fun attach(view: CustomListContract.View) {
        this.view = view
    }

    override fun resume(ctx: Context) {
        shopifyAPIInteractor = ShopifyAPIInteractor(ctx)
        shopifyAPIInteractor.attachCustomColView(view)
    }

}
