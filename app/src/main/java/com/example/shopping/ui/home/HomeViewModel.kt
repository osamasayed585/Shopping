package com.example.shopping.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.shopping.model.data_class.ProductItem
import com.example.shopping.model.repository.ProductsRemoteRepositoryImp
import kotlinx.coroutines.launch


class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val ctx = application

    private var repository: ProductsRemoteRepositoryImp = ProductsRemoteRepositoryImp()

    private val _hotProductMLD = MutableLiveData<List<ProductItem>>()
    val hotProductLD: LiveData<List<ProductItem>> = _hotProductMLD

    private val _productBrandsMLD = MutableLiveData<List<ProductItem>>()
    val productBrandsLD: LiveData<List<ProductItem>> = _productBrandsMLD

    fun getHotProducts() = viewModelScope.launch {
        val result = repository.getAllHotProducts()
        _hotProductMLD.value = result

    }

    fun getProductBrands() = viewModelScope.launch {
        val result = repository.getProductBrands()
        _productBrandsMLD.value = result
    }


}