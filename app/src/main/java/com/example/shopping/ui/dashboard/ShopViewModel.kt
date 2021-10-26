package com.example.shopping.ui.dashboard

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.shopping.model.data_class.ProductItem
import com.example.shopping.model.remote.ShopRemoteBuilder
import com.example.shopping.model.remote.ShoppingAPI
import com.example.shopping.model.repository.ProductsRemoteRepositoryImp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShopViewModel(application: Application) : AndroidViewModel(application) {
    private var _listOfProducts: MutableLiveData<List<ProductItem>> =
        MutableLiveData<List<ProductItem>>()
    private val _shopItemLoading: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    private var repository: ProductsRemoteRepositoryImp

    init {
        val service = ShopRemoteBuilder.productBuilder().create(ShoppingAPI::class.java)
        repository = ProductsRemoteRepositoryImp(service)
        _listOfProducts.value = listOf()
        setData()
    }


    private fun setData() {
        _shopItemLoading.value = true

        viewModelScope.launch {
            val items = repository.getALlProduct()

            viewModelScope.launch(Dispatchers.Main) {
                _shopItemLoading.value = false
                _listOfProducts.value = items.body()
            }
        }


        /*viewModelScope.launch {
            viewModelScope.launch(Dispatchers.Main) {
                _shopItemLoading.value = false
                _listOfProducts.value = listOf(
                    ProductItem(),
                    ProductItem(),
                    ProductItem(),
                    ProductItem(),
                    ProductItem(),
                    ProductItem(),
                    ProductItem(),
                    ProductItem(),
                    ProductItem(),
                    ProductItem(),
                    ProductItem(),
                    ProductItem(),
                    ProductItem(),
                    ProductItem(),
                    ProductItem(),
                    ProductItem(),
                    ProductItem(),
                    ProductItem(),
                    ProductItem(),
                )

            }
        }*/

    }


    val listOfProductsLiveData: LiveData<List<ProductItem>> = _listOfProducts
    val shopItemsLoadingLiveData: LiveData<Boolean> = _shopItemLoading
}