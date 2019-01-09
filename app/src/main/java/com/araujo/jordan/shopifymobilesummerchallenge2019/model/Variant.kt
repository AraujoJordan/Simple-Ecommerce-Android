package com.araujo.jordan.shopifymobilesummerchallenge2019.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Variant Model Class
 * Uses Kotlin Data Class
 * SerializedName for the Retrofit speed inject the variables from API results
 * Parcelable for easily pass the Variant thought Activities
 *  Created by araujojordan on 08/01/2019
 */
@Parcelize
data class Variant (
    @SerializedName("id") val id: Int,
    @SerializedName("product_id") val product_id: String,
    @SerializedName("title") val title: String,
    @SerializedName("created_at") val created_at: String,
    @SerializedName("updated_at") val updated_at: String,
    @SerializedName("option1") val option1: String?,
    @SerializedName("option2") val option2: String?,
    @SerializedName("option3") val option3: String?,
    @SerializedName("inventory_quantity") val inventory_quantity: Int,
    @SerializedName("price") val price: String
) : Parcelable