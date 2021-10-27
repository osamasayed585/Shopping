package com.example.shopping.ui.favourit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shopping.R
import com.example.shopping.databinding.FragmentFavouriteBinding
import com.example.shopping.util.initToolbar


class FavouriteFragment : Fragment() {

    private lateinit var binding : FragmentFavouriteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         initToolbar(binding.toolbar, R.string.my_favourite, true)
    }
}