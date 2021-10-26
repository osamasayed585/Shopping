package com.example.shopping.ui.dashboard

import android.app.Application
import androidx.lifecycle.*
import com.example.shopping.model.data_class.ProductItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ShopViewModel(application: Application) : AndroidViewModel(application) {
    private var _listOfProducts: MutableLiveData<List<ProductItem>> =
        MutableLiveData<List<ProductItem>>()
    private val _shopItemLoading :MutableLiveData<Boolean> = MutableLiveData<Boolean>()

    init {
        _listOfProducts.value = listOf()
        setData()
    }

    private fun setData() {
        _shopItemLoading.value = true

        viewModelScope.launch {
            delay(3000)
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
        }

    }




    val listOfProductsLiveData: LiveData<List<ProductItem>> = _listOfProducts
    val shopItemsLoadingLiveData: LiveData<Boolean> = _shopItemLoading
}