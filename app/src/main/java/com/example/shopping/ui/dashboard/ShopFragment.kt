package com.example.shopping.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.shopping.databinding.FragmentShopBinding
import com.example.shopping.ui.adapter.ProductsItemAdapter


class ShopFragment : Fragment() {

    private lateinit var shopViewModel: ShopViewModel
    private var _binding: FragmentShopBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        shopViewModel =
            ViewModelProvider(this).get(ShopViewModel::class.java)

        _binding = FragmentShopBinding.inflate(inflater, container, false)

        binding.itemRecycler.layoutManager = GridLayoutManager(requireContext(), 2)
        val adapter = ProductsItemAdapter()
        binding.itemRecycler.adapter = adapter
        shopViewModel.listOfProductsLiveData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        shopViewModel.shopItemsLoadingLiveData.observe(viewLifecycleOwner) {
            binding.isLoading.isVisible = it
        }



        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}