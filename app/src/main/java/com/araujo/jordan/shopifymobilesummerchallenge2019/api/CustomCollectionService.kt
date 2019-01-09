package com.araujo.jordan.shopifymobilesummerchallenge2019.api


import com.araujo.jordan.shopifymobilesummerchallenge2019.model.CustomCollection
import com.araujo.jordan.shopifymobilesummerchallenge2019.model.CustomCollectionArray
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * CustomCollectionService
 * Implements the requests itself
 * Created by araujojordan on 08/01/2019.
 */

interface CustomCollectionService {

    @GET("custom_collections.json?access_token=c32313df0d0ef512ca64d5b336a0d7c6")
    fun fetchCol(
        @Query("page") page: Int
    ): Call<CustomCollectionArray>

}