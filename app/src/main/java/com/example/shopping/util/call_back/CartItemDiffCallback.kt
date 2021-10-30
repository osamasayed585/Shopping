package com.example.shopping.util.call_back

import androidx.recyclerview.widget.DiffUtil
import com.example.shopping.model.data_class.CartItem
import com.example.shopping.model.data_class.CategoryItem
import com.example.shopping.model.data_class.ProductItem

class CartItemDiffCallback : DiffUtil.ItemCallback<CartItem>() {
    override fun areItemsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
        return oldItem == newItem
    }
}