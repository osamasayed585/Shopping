package com.example.shopping.model.repository

import com.example.shopping.model.data_class.CartItem
import com.example.shopping.model.data_class.CategoryItem
import com.example.shopping.model.data_class.ProductItem
import retrofit2.Response

interface ProductRepository {
    suspend fun getALlProduct(): List<ProductItem>

    suspend fun getAllCategory(): List<CategoryItem>

    suspend fun getAllCategoryProductsItemsByID(id: String): List<ProductItem>

    suspend fun getAllCategoryProductsItemsByName(name: String): List<ProductItem>

    suspend fun filterProductByQueryName(queryName: String): List<ProductItem>

    suspend fun getAllHotProducts(): List<ProductItem>

    suspend fun getProductBrands(): List<ProductItem>

    suspend fun getProductInCart(): List<CartItem>


}