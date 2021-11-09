package com.example.shopping.model.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.shopping.MApplication
import com.example.shopping.model.data_class.CartItem
import com.example.shopping.model.data_class.ProductItem
import com.example.shopping.model.data_class.RunType
import com.example.shopping.model.local.TestData
import com.example.shopping.model.remote.ShopRemoteBuilder
import com.example.shopping.model.local.DataBaseHelper
import com.example.shopping.model.remote.RemoteBuilder
import com.hrhera.login.model.data.Data
import com.hrhera.login.model.remote.ShoppingAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ProductsRemoteRepositoryImp : ProductRepository {
    private var apiLogin: ShoppingAPI? = null
    private var api: com.example.shopping.model.remote.ShoppingAPI? = null
    private var isOnline = false
    private val dataBase = DataBaseHelper.getInstance(MApplication.getAppContext()).productsDao()
    var allFavouriteProducts: LiveData<List<ProductItem>> = dataBase.allFavouriteProducts


    init {
        isOnline = MApplication.TYPE == RunType.ONLINE
        if (isOnline) {
            api = ShopRemoteBuilder.productBuilder().create(com.example.shopping.model.remote.ShoppingAPI::class.java)
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


    override suspend fun getProductBrands() = withContext(Dispatchers.IO) {
        api = ShopRemoteBuilder.productBuilder()
            .create(com.example.shopping.model.remote.ShoppingAPI::class.java)
        api!!.getProductBrands().body()!!
    }


    override suspend fun getAllHotProducts() = withContext(Dispatchers.IO) {
        api = ShopRemoteBuilder.productBuilder()
            .create(com.example.shopping.model.remote.ShoppingAPI::class.java)
        api!!.getAllProducts().body()!!
    }

    override suspend fun getDiscountArea() = withContext(Dispatchers.IO) {
        api = ShopRemoteBuilder.productBuilder()
            .create(com.example.shopping.model.remote.ShoppingAPI::class.java)
        api!!.getDiscountArea().body()!!
    }

    override suspend fun getProfile(authorization: String) = withContext(Dispatchers.IO){
        apiLogin = RemoteBuilder.builderLogin().create(ShoppingAPI::class.java)
        apiLogin!!.getProfile(authorization)
    }


    override suspend fun postLogin(data: Data) = withContext(Dispatchers.IO){
        apiLogin = RemoteBuilder.builderLogin().create(ShoppingAPI::class.java)
        apiLogin!!.postLogin(data)
    }


    override suspend fun getProductInCart() =
        if (isOnline) {
            withContext(Dispatchers.IO) {
                api!!.getProductInCart(mapOf()).body()!!
            }
        } else {
            withContext(Dispatchers.IO) {
                TestData.getProductInCart().body()!!
            }
        }


    suspend fun addItemToCartList(item: CartItem) =
        if (isOnline) {
            withContext(Dispatchers.IO) {
                api!!.getProductInCart(mapOf()).body()!!
            }
        } else {
            withContext(Dispatchers.IO) {

                TestData.addItemToCart(item).body()!!
            }
        }


    suspend fun checkOtuCart() =
        if (isOnline) {
            withContext(Dispatchers.IO) {
                api!!.getProductInCart(mapOf()).body()!!
            }
        } else {
            withContext(Dispatchers.IO) {
                TestData.checkOut().body()!!
            }
        }


    suspend fun updateItem(count: Int, item: CartItem) = if (isOnline) {
        withContext(Dispatchers.IO) {
            api!!.getProductInCart(mapOf()).body()!!
        }
    } else {
        withContext(Dispatchers.IO) {
            TestData.updateItem(count, item).body()!!
        }
    }


    suspend fun addItemToFavouriteProducts(item: ProductItem) {
        GlobalScope.launch(Dispatchers.IO) {
            dataBase.insert(item)
            Log.e("TAG", "addItemToFavouriteProducts: ${allFavouriteProducts.value?.size}")
        }
    }

    suspend fun deleteFromFavouriteProducts(item: ProductItem) {
        GlobalScope.launch(Dispatchers.IO) {
            dataBase.delete(item.id)
            Log.e(
                "TAG",
                "deleteFromFavouriteProducts:${dataBase.delete(item.id)} ${allFavouriteProducts.value?.size}  id = ${item.id}"
            )

        }
    }


}