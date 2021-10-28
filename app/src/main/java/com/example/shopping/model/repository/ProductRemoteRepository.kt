package com.example.shopping.model.repository

import com.example.shopping.model.data_class.CategoryItem
import com.example.shopping.model.data_class.ProductItem
import retrofit2.Response

interface ProductRemoteRepository {
    suspend fun getALlProduct(): Response<List<ProductItem>>

    suspend fun getAllCategory(): Response<List<CategoryItem>>

    suspend fun getAllCategoryProductsItemsByID(id: String): Response<List<ProductItem>>

    suspend fun getAllCategoryProductsItemsByName(name: String): Response<List<ProductItem>>

    suspend fun filterProductByQueryName(queryName: String): Response<List<ProductItem>>


}