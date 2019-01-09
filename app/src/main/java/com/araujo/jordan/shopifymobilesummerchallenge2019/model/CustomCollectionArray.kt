package com.araujo.jordan.shopifymobilesummerchallenge2019.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * CustomCollectionArray Model Class
 * Uses Kotlin Data Class
 * SerializedName for the Retrofit speed inject the variables from API results
 * Parcelable for easily pass the CustomCollectionArray thought Activities
 *  Created by araujojordan on 08/01/2019
 */
@Parcelize
data class CustomCollectionArray (
    @SerializedName("custom_collections") val custom_collections: List<CustomCollection>,
    @SerializedName("updated_at") val updated_at: String,
    @SerializedName("body_html") val body_html: String,
    @SerializedName("published_at") val published_at: String,
    @SerializedName("sort_order") val sort_order: String,
    @SerializedName("template_suffix") val template_suffix: String,
    @SerializedName("published_scope") val published_scope: String,
    @SerializedName("admin_graphql_api_id") val admin_graphql_api_id: String,
    @SerializedName("image") val image: Image
) : Parcelable