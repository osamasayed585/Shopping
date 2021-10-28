package com.example.shopping.ui.shop

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.shopping.model.data_class.ProductItem
import com.example.shopping.model.repository.DataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShopViewModel(application: Application) : AndroidViewModel(application) {
    /*

    * * ViewModel must doesn't know where the data comes from

    */
    private var repository=DataRepository()


    private var _listOfProducts: MutableLiveData<List<ProductItem>> =
        MutableLiveData<List<ProductItem>>()
    private val _shopItemLoading: MutableLiveData<Boolean> = MutableLiveData<Boolean>()


    init {
        _listOfProducts.value = listOf()
        setData()
    }

    private val mutableListOfItem = mutableListOf<ProductItem>()

    private fun setData() {
        _shopItemLoading.value = true

        viewModelScope.launch {
            val items = repository.getALlProduct()

            viewModelScope.launch(Dispatchers.Main) {
                _shopItemLoading.value = false
                _listOfProducts.value = items
                mutableListOfItem.clear()
                mutableListOfItem.addAll(items)
            }
        }

    }


    val listOfProductsLiveData: LiveData<List<ProductItem>> = _listOfProducts
    val shopItemsLoadingLiveData: LiveData<Boolean> = _shopItemLoading


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




