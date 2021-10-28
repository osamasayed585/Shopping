package com.example.shopping.model.repository

import com.example.shopping.model.data_class.CategoryItem
import com.example.shopping.model.remote.ShoppingAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class ProductsRemoteRepositoryImp(private val api: ShoppingAPI) : ProductRemoteRepository {
    override suspend fun getALlProduct() = withContext(Dispatchers.IO) {
    //    api.getAllProducts()
        TestData.getAllProducts()

    }

    override suspend fun getAllCategory()= withContext(Dispatchers.IO)  {
//        api.getAllCategory()
        TestData.getCategory()
    }


    override suspend fun getAllCategoryProductsItemsByID(id: String) = withContext(Dispatchers.IO) {
        TestData.getCategoryProductByID(id)

    }


    override suspend fun getAllCategoryProductsItemsByName(name: String) = withContext(Dispatchers.IO) {
        TestData.getCategoryProductByName(name)
    }

}