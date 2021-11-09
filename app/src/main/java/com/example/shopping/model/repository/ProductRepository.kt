package com.example.shopping.model.repository

import com.example.shopping.model.data.User
import com.example.shopping.model.data_class.CartItem
import com.example.shopping.model.data_class.CategoryItem
import com.example.shopping.model.data_class.ProductItem
import com.hrhera.login.model.data.Data
import retrofit2.Call
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

    suspend fun getDiscountArea(): List<ProductItem>

    suspend fun getProfile(authorization: String): Response<User>

    suspend fun postLogin(data: Data): Response<User>


}