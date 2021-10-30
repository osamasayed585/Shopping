package com.example.shopping.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shopping.R
import com.example.shopping.databinding.RowCartItemBinding
import com.example.shopping.model.data_class.CartItem
import com.example.shopping.util.call_back.CartItemDiffCallback
import com.example.shopping.util.call_back.OnRecyclerItemClick
import com.squareup.picasso.Picasso

class OrderRecyclerAdapter() :
    ListAdapter<CartItem, OrderRecyclerAdapter.CartItemViewHolder>(
        CartItemDiffCallback()
    ) {
    lateinit var onRemoveFullItemClick: OnRecyclerItemClick
    lateinit var onRemoveOneClick: OnRecyclerItemClick
    lateinit var onAddOneClick: OnRecyclerItemClick

    override fun submitList(list: List<CartItem>?) {
        super.submitList(list)
        notifyDataSetChanged()
    }

    class CartItemViewHolder(val bind: RowCartItemBinding) :
        RecyclerView.ViewHolder(bind.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemViewHolder {
        return CartItemViewHolder(
            RowCartItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CartItemViewHolder, position: Int) {
        val item = getItem(position)
        Picasso.get().load(item.item.image1).centerCrop().fit().into(holder.bind.image)
        holder.bind.count.text = item.count.toString()

        if (item.count == 0) {
            holder.bind.count.text = holder.itemView.context.getText(R.string.zero_count_val)
        }

        holder.bind.title.text = item.item.title
        holder.bind.price.text = item.item.price
        holder.bind.addOne.setOnClickListener {
            onAddOneClick.onItemClick(item)
        }

        holder.bind.removeItem.setOnClickListener {
            onRemoveFullItemClick.onItemClick(item)
        }

        holder.bind.removeOne.setOnClickListener {
            onRemoveOneClick.onItemClick(item)
        }
    }


}