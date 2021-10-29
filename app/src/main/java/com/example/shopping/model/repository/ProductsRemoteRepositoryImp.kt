package com.example.shopping.model.repository

import com.example.shopping.MApplication
import com.example.shopping.model.data_class.RunType
import com.example.shopping.model.local.TestData
import com.example.shopping.model.remote.ShopRemoteBuilder
import com.example.shopping.model.remote.ShoppingAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductsRemoteRepositoryImp() : ProductRepository {
    private var api: ShoppingAPI? = null
    private var isOnline = false

    init {
        isOnline = MApplication.TYPE == RunType.ONLINE
        if (isOnline) {
            api = ShopRemoteBuilder.productBuilder().create(ShoppingAPI::class.java)
        }
    }


    override suspend fun getALlProduct() = if (isOnline) {
        withContext(Dispatchers.IO) {
            api!!.getAllProducts().body()!!

        }
    } else {
        withContext(Dispatchers.IO) {
            TestData.getAllProducts().body()!!
        }
    }

    override suspend fun getAllCategory() = if (isOnline) {
        withContext(Dispatchers.IO) {
            api!!.getAllCategory().body()!!
        }
    } else {
        withContext(Dispatchers.IO) {
            TestData.getCategory().body()!!
        }
    }


    override suspend fun getAllCategoryProductsItemsByID(id: String) = if (isOnline) {
        withContext(Dispatchers.IO) {
            api!!.getCategoryProductByID(id).body()!!

        }
    } else {
        withContext(Dispatchers.IO) {
            TestData.getCategoryProductByID(id).body()!!
        }
    }


    override suspend fun getAllCategoryProductsItemsByName(name: String) =
        if (isOnline) {
            withContext(Dispatchers.IO) {
                api!!.getCategoryProductByName(name).body()!!
            }
        } else {
            withContext(Dispatchers.IO) {
                TestData.getCategoryProductByName(name).body()!!
            }
        }

    override suspend fun filterProductByQueryName(queryName: String) =
        if (isOnline) {
            withContext(Dispatchers.IO) {
                api!!.filterProductByQueryName(queryName).body()!!
            }
        } else {
            withContext(Dispatchers.IO) {
                TestData.filterProductByQueryName(queryName).body()!!
            }
        }

}