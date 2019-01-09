package com.araujo.jordan.shopifymobilesummerchallenge2019.contract

import com.araujo.jordan.shopifymobilesummerchallenge2019.model.Collection
import com.araujo.jordan.shopifymobilesummerchallenge2019.model.CustomCollection


/**
 * Manage the control or ViewProjection layers
 * Created by araujojordan on 18/12/2018.
 */

class CollectionListContract {

    interface View: BaseView  {
        fun showLoading(isLoading: Boolean)
        fun showErrorMessage(error: String)
        fun fetchColSuccess(collects: List<Collection>?)
    }

    interface Presenter:BasePresenter<View>{
        fun fetchColData(page:Int,colID:Int)
    }
}