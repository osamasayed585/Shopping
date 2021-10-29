package com.example.shopping.model.remote

import com.example.shopping.model.data_class.CategoryItem
import com.example.shopping.model.data_class.ProductItem
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Query

interface ShoppingAPI {
    @GET("shop")
    suspend fun getAllProducts(): Response<List<ProductItem>>

    @GET("category")
    suspend fun getAllCategory(): Response<List<CategoryItem>>

    @GET("shop")
    suspend fun getCategoryProductByID(@Query("id") id: String): Response<List<ProductItem>>

    @GET("shop")
    suspend fun getCategoryProductByName(@Query("name") name: String): Response<List<ProductItem>>


  @GET("shop")
  suspend fun filterProductByQueryName(@Query("name") name: String): Response<List<ProductItem>>


}