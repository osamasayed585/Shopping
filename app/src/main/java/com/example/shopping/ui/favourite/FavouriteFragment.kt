package com.example.shopping.ui.favourite

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.shopping.databinding.FragmentFavouriteBinding
import com.example.shopping.model.data_class.ProductItem
import com.example.shopping.ui.adapter.FavouriteProductsItemAdapter
import com.example.shopping.util.call_back.OnRecyclerItemClick
import com.example.shopping.util.view.BaseFragment


class FavouriteFragment : BaseFragment<FragmentFavouriteBinding, FavouriteViewModel>() {
    override fun getFragmentView() = FragmentFavouriteBinding.inflate(layoutInflater)

    override fun getViewModel() = FavouriteViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = FavouriteProductsItemAdapter()
        binding.showAllFavourite.adapter = adapter

        viewModel.listOfFavouriteProducts.observe(viewLifecycleOwner) {
            val items = mutableListOf<ProductItem>()
            items.addAll(it)
            adapter.submitList(items)
        }
        adapter.onAddToCartClick = object : OnRecyclerItemClick {
            override fun onItemClick(item: Any) {
                viewModel.addItemToCart(item as ProductItem)
                Toast.makeText(requireContext(), "Done", Toast.LENGTH_SHORT).show()
            }
        }

        adapter.onRemoveFromFavouriteClick= object : OnRecyclerItemClick {
            override fun onItemClick(item: Any) {
                viewModel.removeFromFavourite(item  as ProductItem)
            }
        }
    }
}