package com.example.shopping.util.call_back

import androidx.recyclerview.widget.DiffUtil
import com.example.shopping.model.data_class.ProductItem

class ProductItemDiffCallback : DiffUtil.ItemCallback<ProductItem>() {
    override fun areItemsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean {
        return oldItem == newItem
    }
}