package com.araujo.jordan.shopifymobilesummerchallenge2019.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Collection Model Class
 * Uses Kotlin Data Class
 * SerializedName for the Retrofit speed inject the variables from API results
 * Parcelable for easily pass the Collection thought Activities
 *  Created by araujojordan on 08/01/2019
 */
@Parcelize
data class Collection (
    @SerializedName("id") val id: Int,
    @SerializedName("collection_id") val collection_id: String,
    @SerializedName("product_id") val product_id: String,
    @SerializedName("created_at") val created_at: String,
    @SerializedName("updated_at") val updated_at: String
) : Parcelable