package com.araujo.jordan.shopifymobilesummerchallenge2019.contract

import com.araujo.jordan.shopifymobilesummerchallenge2019.model.Product


/**
 * Manage the control or ViewProjection layers
 * Created by araujojordan on 18/12/2018.
 */

class ProductListContract {

    interface View : BaseView {
        fun showLoading(isLoading: Boolean)
        fun showErrorMessage(error: String)
        fun fetchProductsSuccess(list: List<Product>?)
    }

    interface Presenter : BasePresenter<View> {
        fun fetchProdData(page: Int, products: String)
    }
}