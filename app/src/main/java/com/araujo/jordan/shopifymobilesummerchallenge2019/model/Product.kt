package com.araujo.jordan.shopifymobilesummerchallenge2019.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Product Model Class
 * Uses Kotlin Data Class
 * SerializedName for the Retrofit speed inject the variables from API results
 * Parcelable for easily pass the Product thought Activities
 *  Created by araujojordan on 08/01/2019
 */
@Parcelize
data class Product (
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("body_html") val body_html: String,
    @SerializedName("vendor") val vendor: String,
    @SerializedName("created_at") val created_at: String,
    @SerializedName("handle") val handle: String,
    @SerializedName("updated_at") val updated_at: String,
    @SerializedName("published_at") val published_at: String,
    @SerializedName("tags") val tags: String,
    @SerializedName("image") val image: Image,
    @SerializedName("product_type") val product_type: String
) : Parcelable