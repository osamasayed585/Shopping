package com.example.shopping.ui.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.shopping.model.data_class.ProductItem
import com.example.shopping.model.remote.ShopRemoteBuilder
import com.example.shopping.model.remote.ShoppingAPI
import com.example.shopping.model.repository.ProductsRemoteRepositoryImp
import com.hrhera.login.utils.Constants.Companion.TAG
import kotlinx.coroutines.launch


class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val ctx = application

    private var repository: ProductsRemoteRepositoryImp

    private val _hotProductMLD = MutableLiveData<List<ProductItem>>()
    val hotProductLD: LiveData<List<ProductItem>> = _hotProductMLD

    private val _productBrandsMLD = MutableLiveData<List<ProductItem>>()
    val productBrandsLD: LiveData<List<ProductItem>> = _productBrandsMLD

    init {
        val service = ShopRemoteBuilder.productBuilder().create(ShoppingAPI::class.java)
        repository = ProductsRemoteRepositoryImp(service)
    }

    fun getHotProducts() = viewModelScope.launch {
        val result = repository.getAllHotProducts()
        if (result.body() != null && result.isSuccessful){
            _hotProductMLD.value = result.body()
        }else{
            Log.i(TAG, "getHotProducts: ${result.message()}")
        }
    }

    fun getProductBrands() = viewModelScope.launch {
        val result = repository.getProductBrands()
        if (result.body() != null && result.isSuccessful){
            _productBrandsMLD.value = result.body()
        }else{
            Log.i(TAG, "getProductBrands: ${result.message()}")
        }
    }



}