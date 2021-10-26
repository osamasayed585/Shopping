package com.example.shopping.model.repository

import com.example.shopping.model.remote.ShoppingAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductsRemoteRepositoryImp(private val api: ShoppingAPI) : ProductRemoteRepository {
    override suspend fun getALlProduct() = withContext(Dispatchers.IO) {
        api.getAllProducts()
    }


}