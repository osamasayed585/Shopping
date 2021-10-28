package com.example.shopping.model.repository

import com.example.shopping.model.data_class.CategoryItem
import com.example.shopping.model.data_class.ProductItem
import com.example.shopping.model.remote.ProductsRemoteRepositoryImp
import com.example.shopping.model.remote.ShopRemoteBuilder
import com.example.shopping.model.remote.ShoppingAPI

class DataRepository {

    /*

    * * doing some check to return data from api or local database

     */

    private val service = ShopRemoteBuilder.productBuilder().create(ShoppingAPI::class.java)
    private val repository = ProductsRemoteRepositoryImp(service)


    suspend fun getALlProduct(): List<ProductItem> {
        return  repository.getALlProduct().body()?: listOf()
    }

    suspend fun getAllCategory(): List<CategoryItem> {
        return  repository.getAllCategory().body()?: listOf()

    }

    suspend fun getAllCategoryProductsItemsByID(id: String): List<ProductItem> {
        return  repository.getAllCategoryProductsItemsByID(id).body()?: listOf()

    }

    suspend fun getAllCategoryProductsItemsByName(name: String): List<ProductItem> {
        return  repository.getAllCategoryProductsItemsByName(name).body()?: listOf()

    }

    suspend fun filterProductByQueryName(queryName: String): List<ProductItem> {
        return  repository.filterProductByQueryName(queryName).body()?: listOf()
    }
}