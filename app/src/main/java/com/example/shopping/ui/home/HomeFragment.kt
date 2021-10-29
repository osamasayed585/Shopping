package com.example.shopping.ui.home

import android.graphics.Point
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.example.shopping.R
import com.example.shopping.databinding.FragmentHomeBinding
import com.example.shopping.ui.adapter.HotProductViewPagerAdapter
import com.example.shopping.ui.adapter.ProductBrandsViewPagerAdapter


class HomeFragment : Fragment() {

    private lateinit var hotProductViewPager: ViewPager
    private lateinit var productBrandsViewPager: ViewPager
    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewPager(view)

        getProductBrands()
        initProductBrandsObserve()

        getHotProducts()
        initHotProductObserve()

    }

    private fun initHotProductObserve() {
        homeViewModel.hotProductLD.observe(viewLifecycleOwner, {
            hotProductViewPager.adapter = HotProductViewPagerAdapter(requireContext(), it)
        })
    }

    private fun initProductBrandsObserve() {
        homeViewModel.productBrandsLD.observe(viewLifecycleOwner, {
            productBrandsViewPager.adapter = ProductBrandsViewPagerAdapter(requireContext(), it)
        })
    }

    private fun getHotProducts() {
        homeViewModel.getHotProducts()
    }

    private fun getProductBrands() {
        homeViewModel.getProductBrands()
    }

    private fun initViewPager(view: View) {

        productBrandsViewPager = view.findViewById(R.id.productBrands_view_pager)
        productBrandsViewPager.clipToPadding = false
        productBrandsViewPager.setPadding(0,0,(getScreenWidth()*0.2).toInt(),0)


        hotProductViewPager = view.findViewById(R.id.hotProduct_view_pager)
        hotProductViewPager.clipToPadding = false
        hotProductViewPager.setPadding(0, 0, (getScreenWidth() * 0.6).toInt(), 0)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getScreenWidth(): Int {

        val display = requireActivity().windowManager.defaultDisplay

        val density = resources.displayMetrics.density
        val size = Point();
        display.getSize(size);
        val width = size.x;
        val height = size.y;
        Log.e("Width", "" + width);
        Log.e("height", "" + height);

        return width
    }
}