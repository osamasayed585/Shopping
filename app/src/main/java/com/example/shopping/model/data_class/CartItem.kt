package com.example.shopping.model.data_class

data class CartItem(var id: String="", var count: Int, var item: ProductItem) {
    fun getTotal(): Float {
        var rowTotal = 0f
        rowTotal = item.price.toFloat() * count
        return rowTotal
    }
}