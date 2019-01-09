package com.araujo.jordan.shopifymobilesummerchallenge2019.contract

import com.araujo.jordan.shopifymobilesummerchallenge2019.api.ShopifyAPIInteractor


/**
 * BeerListPresenter Class
 * Parcelable for easily pass the Beer thought Activities
 * Created by araujojordan on 08/01/2019
 */

open class CollectionListPresenter : CollectionListContract.Presenter {


    private lateinit var shopifyAPIInteractor: ShopifyAPIInteractor
    lateinit var view: CollectionListContract.View

    override fun resume() {
        shopifyAPIInteractor = ShopifyAPIInteractor()
        shopifyAPIInteractor.attachColView(view)
    }

    override fun attach(view: CollectionListContract.View) {
        this.view = view
    }

    override fun fetchColData(page: Int, colID: Int) {
        view.showLoading(true)
        shopifyAPIInteractor.fetchCollections(page,colID)
    }

}
