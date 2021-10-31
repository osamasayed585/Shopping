package com.example.shopping.ui.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopping.model.data_class.CartItem
import com.example.shopping.model.data_class.ProductItem
import com.example.shopping.model.repository.ProductsRemoteRepositoryImp
import kotlinx.coroutines.launch

class FavouriteViewModel : ViewModel() {
    private var repository: ProductsRemoteRepositoryImp = ProductsRemoteRepositoryImp()
    fun removeFromFavourite(item: ProductItem) {
        viewModelScope.launch {
            repository.deleteFromFavouriteProducts(item)
        }
    }

    val listOfFavouriteProducts = repository.allFavouriteProducts

    fun addItemToCart(item: ProductItem) {
        val cartItem = CartItem(count = 1, item = item)
        viewModelScope.launch {
            repository.addItemToCartList(cartItem)
        }
    }

}