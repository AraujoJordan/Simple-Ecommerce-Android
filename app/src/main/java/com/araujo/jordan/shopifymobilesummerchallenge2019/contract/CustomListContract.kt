package com.araujo.jordan.shopifymobilesummerchallenge2019.contract

import com.araujo.jordan.shopifymobilesummerchallenge2019.model.Collection
import com.araujo.jordan.shopifymobilesummerchallenge2019.model.CustomCollection


/**
 * Manage the control or ViewProjection layers
 * Created by araujojordan on 18/12/2018.
 */

class CustomListContract {

    interface View: BaseView  {
        fun showLoading(isLoading: Boolean)
        fun showErrorMessage(error: String)
        fun fetchCustColSuccess(list: List<CustomCollection>?)
    }

    interface Presenter:BasePresenter<View>{
        fun fetchCCData(page:Int)
    }
}