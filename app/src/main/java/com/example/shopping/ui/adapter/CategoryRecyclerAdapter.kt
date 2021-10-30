package com.example.shopping.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shopping.databinding.RowCategoryItemWithIconBinding
import com.example.shopping.databinding.RowCategoryItemWithPhotoBinding
import com.example.shopping.model.data_class.CategoryItem
import com.example.shopping.util.view.CircleTransform
import com.example.shopping.util.call_back.CategoryItemDiffCallback
import com.example.shopping.util.call_back.OnRecyclerItemClick
import com.squareup.picasso.Picasso

class CategoryRecyclerAdapter(val withIcon: Boolean = true) :
    ListAdapter<CategoryItem, RecyclerView.ViewHolder>(
        CategoryItemDiffCallback()
    ) {
    lateinit var onClick: OnRecyclerItemClick


    class CategoryWithIconViewHolder(val bind: RowCategoryItemWithIconBinding) :
        RecyclerView.ViewHolder(bind.root)

    class CategoryWithPhotoViewHolder(val bind: RowCategoryItemWithPhotoBinding) :
        RecyclerView.ViewHolder(bind.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (withIcon) {
            return CategoryWithIconViewHolder(
                RowCategoryItemWithIconBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                )
            )
        }
        return CategoryWithPhotoViewHolder(
            RowCategoryItemWithPhotoBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)

        holder.itemView.setOnClickListener {
            onClick.onItemClick(item)
        }


        if (withIcon) {
            val bind = (holder as CategoryWithIconViewHolder).bind
            Picasso.get().load(item.image)
                .centerCrop()
                .fit()
                .transform(CircleTransform())
                .into(bind.icon)

            bind.title.text = item.title



        } else {

            val bind = (holder as CategoryWithPhotoViewHolder).bind

            Picasso.get().load(item.image)
                .centerCrop()
                .fit()
                .into(bind.icon)

            bind.title.text = item.title
        }


    }


}