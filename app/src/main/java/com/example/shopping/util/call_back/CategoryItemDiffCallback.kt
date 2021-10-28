package com.example.shopping.util.call_back

import androidx.recyclerview.widget.DiffUtil
import com.example.shopping.model.data_class.CategoryItem
import com.example.shopping.model.data_class.ProductItem

class CategoryItemDiffCallback : DiffUtil.ItemCallback<CategoryItem>() {
    override fun areItemsTheSame(oldItem: CategoryItem, newItem: CategoryItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CategoryItem, newItem: CategoryItem): Boolean {
        return oldItem == newItem
    }
}