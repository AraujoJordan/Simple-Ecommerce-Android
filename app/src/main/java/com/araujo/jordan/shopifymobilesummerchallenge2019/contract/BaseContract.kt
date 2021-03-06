package com.araujo.jordan.shopifymobilesummerchallenge2019.contract

import android.content.Context

/**
 * BasePresenter
 * Abstract Presenter that would be implement in many Presenters if the app scale more
 * Created by araujojordan on 08/01/19.
 */

interface BasePresenter<in T> {
    fun attach(view: T)
    fun resume(ctx: Context)

}

interface BaseView

