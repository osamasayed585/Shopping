package com.example.shopping.ui.home

import android.app.Application
import android.os.Build
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.shopping.R
import com.example.shopping.model.data_class.CartItem
import com.example.shopping.model.data_class.ProductItem
import com.example.shopping.model.repository.ProductsRemoteRepositoryImp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: ProductsRemoteRepositoryImp = ProductsRemoteRepositoryImp()

    private val _hotProductMLD = MutableLiveData<List<ProductItem>>()
    val hotProductLD: LiveData<List<ProductItem>> = _hotProductMLD

    private val _productBrandsMLD = MutableLiveData<List<ProductItem>>()
    val productBrandsLD: LiveData<List<ProductItem>> = _productBrandsMLD

    private val _discountAreaMLD = MutableLiveData<List<ProductItem>>()
    val discountAreaLD: LiveData<List<ProductItem>> = _discountAreaMLD

    val addCartItem = MutableLiveData<List<CartItem>>()

    private var _listOfProducts: MutableLiveData<List<ProductItem>> =
        MutableLiveData<List<ProductItem>>()
    val listOfProductsLiveData: LiveData<List<ProductItem>> = _listOfProducts

    private val mutableListOfItem = mutableListOf<ProductItem>()



    fun getHotProducts() = viewModelScope.launch {
        val result = repository.getALlProduct()
        _hotProductMLD.value = result
        mutableListOfItem.clear()
        mutableListOfItem.addAll(result)
    }

    fun getProductBrands() = viewModelScope.launch {
        val result = repository.getALlProduct()
        _productBrandsMLD.value = result
        mutableListOfItem.clear()
        mutableListOfItem.addAll(result)
    }

    fun getDiscountArea() = viewModelScope.launch {
        val result = repository.getALlProduct()
        _discountAreaMLD.value = result
        mutableListOfItem.clear()
        mutableListOfItem.addAll(result)
    }

    fun addItemToCartFromHome(item: ProductItem) {
        val cartItem = CartItem(count = 1, item = item)
        viewModelScope.launch {
            addCartItem.postValue(repository.addItemToCartList(cartItem))
        }
    }

    fun filterByProductName(queryName: String) {

        if (queryName.length > 2) {
            viewModelScope.launch {
                val value = repository.filterProductByQueryName(queryName)
                _listOfProducts.value = value
            }
            return
        }
        _listOfProducts.value = mutableListOfItem
    }


}