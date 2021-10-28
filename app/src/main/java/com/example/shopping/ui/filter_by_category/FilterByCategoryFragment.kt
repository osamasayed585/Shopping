package com.example.shopping.ui.filter_by_category

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shopping.R
import com.example.shopping.databinding.FragmentFilterByCategoryBinding

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









}