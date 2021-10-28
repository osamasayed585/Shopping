package com.example.shopping.model.remote

import com.example.shopping.model.data_class.ProductItem
import retrofit2.Response
import retrofit2.http.GET

interface ShoppingAPI {
    @GET("shop")
    suspend fun getAllProducts(): Response<List<ProductItem>>

    @GET("category")
    suspend fun getAllCategory(): Response<List<ProductItem>>


}