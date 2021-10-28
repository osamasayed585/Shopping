package com.example.shopping.ui.filter_by_category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.example.shopping.databinding.FragmentFilterByCategoryBinding
import com.example.shopping.ui.adapter.ProductsItemAdapter
import com.example.shopping.ui.main.MainActivity
import com.squareup.picasso.Picasso

class FilterByCategoryFragment : Fragment() {

    private var _binding: FragmentFilterByCategoryBinding? = null
    private val binding get() = _binding!!

    private lateinit var sherViewModel: FilterByCategoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilterByCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sherViewModel = (requireActivity() as MainActivity).sherViewModel
        val adapter = ProductsItemAdapter()
        binding.itemList.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.itemList.adapter = adapter

        sherViewModel.categoryItemsMutableLiveData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        sherViewModel.loaderMutableLiveData.observe(viewLifecycleOwner) {
            binding.productLoader.isVisible = it
        }
        binding.topView.visibility=View.GONE
        sherViewModel.categoryItemMutableLiveData.observe(viewLifecycleOwner) {
            Picasso.get().load(it.image).fit().centerCrop().into(binding.categoryImage)
            binding.categoryTitle.text = it.title
        }

    }


}