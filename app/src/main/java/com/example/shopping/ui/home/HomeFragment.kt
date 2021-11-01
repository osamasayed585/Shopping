package com.example.shopping.ui.home

import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContentProviderCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.example.shopping.R
import com.example.shopping.databinding.FragmentHomeBinding
import com.example.shopping.model.data_class.ProductItem
import com.example.shopping.ui.adapter.DiscountAreaViewPagerAdapter
import com.example.shopping.ui.adapter.HotProductViewPagerAdapter
import com.example.shopping.ui.adapter.ProductBrandsViewPagerAdapter
import com.example.shopping.ui.adapter.ProductsItemAdapter
import com.example.shopping.ui.main.MainActivity
import com.example.shopping.util.call_back.OnRecyclerItemClick


class HomeFragment : Fragment() {

    private lateinit var hotProductViewPager: ViewPager
    private lateinit var productBrandsViewPager: ViewPager
    private lateinit var discountAreaViewPager: ViewPager
    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private lateinit var countCart: TextView

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
        initHomeMenu()

        initViewPager(view)

        getProductBrands()
         initProductBrandsObserve()

        getHotProducts()
        initHotProductObserve()

        getDiscountArea()
        initDiscountAreaObserve()

        homeViewModel.listOfProductsLiveData.observe(viewLifecycleOwner, {
            Toast.makeText(context, "observe search", Toast.LENGTH_SHORT).show()
        })

    }

    private fun initHomeMenu() {
        val item: MenuItem = binding.toolbar.menu.findItem(R.id.orders)
        val v: View = item.actionView!!
        countCart = v.findViewById(R.id.cartNotification)

        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.search -> {
                    initSearchView(it)
                    true
                }
                R.id.favourite -> {
                    (requireActivity() as MainActivity).navController.navigate(R.id.favouriteFragment)
                    true
                }
                R.id.orders -> {
                    (requireActivity() as MainActivity).navController.navigate(R.id.ordersFragment)
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    private fun initDiscountAreaObserve() {
        homeViewModel.discountAreaLD.observe(viewLifecycleOwner, {
           discountAreaViewPager.adapter = DiscountAreaViewPagerAdapter(requireContext(), it)
        })
    }

    private fun getDiscountArea() {
        homeViewModel.getDiscountArea()
    }

    private fun initHotProductObserve() {
        homeViewModel.hotProductLD.observe(viewLifecycleOwner, {
            hotProductViewPager.adapter = HotProductViewPagerAdapter(requireContext(),it)
        })

        HotProductViewPagerAdapter.addToCartClick = object : OnRecyclerItemClick{
            override fun onItemClick(item: Any) {
                homeViewModel.addItemToCartFromHome(item as ProductItem)
                Toast.makeText(requireContext(), "Done", Toast.LENGTH_SHORT).show()
            }

        }

        homeViewModel.addCartItem.observe(viewLifecycleOwner,{
            countCart.isVisible = it.isNotEmpty()
            countCart.text = "${it.size}"
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

        discountAreaViewPager = view.findViewById(R.id.discountArea_view_pager)
        discountAreaViewPager.clipToPadding = false
        discountAreaViewPager.setPadding(0, 0,  (getScreenWidth() * 0.6).toInt(), 0)
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

    private fun initSearchView(it: MenuItem): Boolean {
        val searchView: SearchView = it.actionView as SearchView
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            searchView.foregroundTintList =
                ContextCompat.getColorStateList(requireContext(), R.color.white)
        }
        searchView.setPaddingRelative(20, 0, 20, 0)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                homeViewModel.filterByProductName(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                homeViewModel.filterByProductName(newText)
                return false
            }
        })
        return false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}