package com.example.shopping.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shopping.R
import com.example.shopping.databinding.RowOneProductItemBinding

import com.example.shopping.model.data_class.ProductItem
import com.example.shopping.util.call_back.OnRecyclerItemClick
import com.example.shopping.util.call_back.ProductItemDiffCallback
import com.squareup.picasso.Picasso

class ProductsItemAdapter : ListAdapter<ProductItem, ProductsItemAdapter.ProductViewHolder>(
    ProductItemDiffCallback()
) {
    class ProductViewHolder(val bind: RowOneProductItemBinding) : RecyclerView.ViewHolder(bind.root)

    private val listOfFavourite = mutableListOf<ProductItem>()

    fun setFavList(listProductItem: List<ProductItem>) {
        listOfFavourite.clear()
        listOfFavourite.addAll(listProductItem)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            RowOneProductItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    lateinit var onAddToCartClick: OnRecyclerItemClick
    lateinit var onAddToFavouriteClick: OnRecyclerItemClick
    lateinit var onRemoveFromFavouriteClick: OnRecyclerItemClick

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item = getItem(position)
        val bind = holder.bind
        bind.productName.text = item.title
        bind.productPrice.text = item.price
        Picasso.get().load(item.image1)
            .centerCrop()
            .fit()
            .into(bind.productImage)
        bind.productRating.rating = Float.fromBits(item.rating)

        bind.shopImage.setOnClickListener {
            onAddToCartClick.onItemClick(item)
        }

        try {

            bind.likeImage.setColorFilter(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.grey_500
                )
            )

            bind.likeImage.setOnClickListener {
                onAddToFavouriteClick.onItemClick(item)
            }

            if (isFavourite(item)) {
                bind.likeImage.setColorFilter(
                    ContextCompat.getColor(
                        holder.itemView.context,
                        R.color.red
                    )
                )
                bind.likeImage.setOnClickListener {
                    onRemoveFromFavouriteClick.onItemClick(item)
                }
            }

        }catch (e:Throwable){
            Log.e("TAG", "onBindViewHolder: ${e.message}", )
        }

    }


    private fun isFavourite(item: ProductItem): Boolean {
        return listOfFavourite.filter { it.id == item.id }.size == 1
    }
}