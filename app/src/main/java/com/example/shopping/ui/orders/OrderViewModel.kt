package com.example.shopping.ui.orders

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopping.model.data_class.CartItem
import com.example.shopping.model.repository.ProductsRemoteRepositoryImp
import kotlinx.coroutines.launch


class OrderViewModel : ViewModel() {

    private val repository: ProductsRemoteRepositoryImp = ProductsRemoteRepositoryImp()

    val orderCartItemLiveData = MutableLiveData<List<CartItem>>()

    init {
        viewModelScope.launch {
            val items = repository.getProductInCart()
            calcTotal(items)
            orderCartItemLiveData.postValue(items)
        }

    }


    fun addOne(item: CartItem) {

        viewModelScope.launch {
            val items = repository.updateItem(1, item)
            orderCartItemLiveData.postValue(items)
            calcTotal(items)
        }
    }


    fun removeOne(item: CartItem) {
        viewModelScope.launch {
            val items = repository.updateItem(-1, item)
            orderCartItemLiveData.postValue(items)
            calcTotal(items)
        }

    }

    fun removeFullItem(item: CartItem) {

        viewModelScope.launch {
            val items = repository.updateItem(0, item)
            orderCartItemLiveData.postValue(items)
            calcTotal(items)

        }

    }

    val orderCartItemTotalLiveData = MutableLiveData<String>()

    private fun calcTotal(items: List<CartItem>) {
        var total = 0f

        for (i in items.filter { it.count > 0 }) {
            total += i.getTotal()
        }

        orderCartItemTotalLiveData.postValue(total.toString())
    }


}