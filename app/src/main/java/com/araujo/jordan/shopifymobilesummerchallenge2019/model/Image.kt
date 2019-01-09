package com.araujo.jordan.shopifymobilesummerchallenge2019.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Image Model Class
 * Uses Kotlin Data Class
 * SerializedName for the Retrofit speed inject the variables from API results
 * Parcelable for easily pass the Image thought Activities
 *  Created by araujojordan on 08/01/2019
 */
@Parcelize
data class Image (
    @SerializedName("src") val src: String,
    @SerializedName("width") val width: Int,
    @SerializedName("height") val height: Int
) : Parcelable