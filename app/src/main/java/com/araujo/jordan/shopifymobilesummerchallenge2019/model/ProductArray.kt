package com.araujo.jordan.shopifymobilesummerchallenge2019.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * ProductArray Model Class
 * Uses Kotlin Data Class
 * SerializedName for the Retrofit speed inject the variables from API results
 * Parcelable for easily pass the ProductArray thought Activities
 *  Created by araujojordan on 08/01/2019
 */
@Parcelize
data class ProductArray (
    @SerializedName("products") val products: List<Product>
) : Parcelable