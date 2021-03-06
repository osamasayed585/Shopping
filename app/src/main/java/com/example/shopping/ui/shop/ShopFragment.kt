package com.example.shopping.ui.shop

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.shopping.R
import com.example.shopping.databinding.FragmentShopBinding
import com.example.shopping.model.data_class.CategoryItem
import com.example.shopping.model.data_class.ProductItem
import com.example.shopping.ui.adapter.CategoryRecyclerAdapter
import com.example.shopping.ui.adapter.ProductsItemAdapter
import com.example.shopping.ui.filter_by_category.FilterByCategoryViewModel
import com.example.shopping.ui.main.MainActivity
import com.example.shopping.util.call_back.OnRecyclerItemClick
import androidx.core.view.isVisible as isVisible1


class ShopFragment : Fragment() {

    private lateinit var shopViewModel: ShopViewModel
    private lateinit var sharedModel: FilterByCategoryViewModel
    private lateinit var countCart: TextView

    private var _binding: FragmentShopBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        shopViewModel =
            ViewModelProvider(this)[ShopViewModel::class.java]

        sharedModel = (requireActivity() as MainActivity).sherViewModel

        _binding = FragmentShopBinding.inflate(inflater, container, false)

        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val item: MenuItem = binding.toolbar.menu.findItem(R.id.orders)
        val v: View = item.actionView!!
        countCart = v.findViewById(R.id.cartNotification)
        countCart.setOnClickListener {
            (requireActivity() as MainActivity).navController.navigate(R.id.ordersFragment)
        }

        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.search -> {
                    initSearchView(it)
                }
                R.id.favourite -> {
                    (requireActivity() as MainActivity).navController.navigate(R.id.favouriteFragment)
                    false
                }
                R.id.orders -> {
                    (requireActivity() as MainActivity).navController.navigate(R.id.ordersFragment)
                    false
                }
                else -> {
                    false
                }
            }
        }


        initMainFun()
        handleCategoryView()


    }



    private fun initMainFun() {
        val adapter = ProductsItemAdapter()

        binding.itemRecycler.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.itemRecycler.adapter = adapter

        adapter.onAddToCartClick = object : OnRecyclerItemClick {
            override fun onItemClick(item: Any) {
                shopViewModel.addItemToCart(item as ProductItem)
                Toast.makeText(requireContext(), "Done", Toast.LENGTH_SHORT).show()
            }
        }

        adapter.onRemoveFromFavouriteClick= object : OnRecyclerItemClick {
            override fun onItemClick(item: Any) {
                shopViewModel.removeFromFavourite(item  as ProductItem)
            }
        }


        adapter.onAddToFavouriteClick= object : OnRecyclerItemClick {
            override fun onItemClick(item: Any) {
                shopViewModel.addToFavourite(item  as ProductItem)
            }
        }




        shopViewModel.listOfProductsLiveData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        shopViewModel.shopItemsLoadingLiveData.observe(viewLifecycleOwner) {
            binding.isLoading.isVisible = it
        }

        shopViewModel.cartItems.observe(viewLifecycleOwner) {
           countCart.isVisible = it.isNotEmpty()
           countCart.text = it.size.toString()

            // look!, this is best
            countCart.isVisible1 = it.isNotEmpty()
            countCart.text = it.size.toString()
        }




        shopViewModel.listOfFavouriteProducts.observe(viewLifecycleOwner) {
            adapter.setFavList(it)
        }


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
                shopViewModel.filterByProductName(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                shopViewModel.filterByProductName(newText)
                return false
            }
        })
        return false
    }


    private fun handleCategoryView() {
        val categoryAdapter = CategoryRecyclerAdapter()
        binding.categoryLayout.categoryRecycler.layoutManager =
            GridLayoutManager(requireContext(), 4)
        binding.categoryLayout.categoryRecycler.adapter = categoryAdapter
        sharedModel.categoryMutableLiveData.observe(
            viewLifecycleOwner
        ) {
            categoryAdapter.submitList(it)
        }
        binding.categoryContainer.setOnClickListener {

            binding.categoryContainer.visibility = View.GONE
            val img = ContextCompat.getDrawable(requireContext(), R.drawable.ic_down_arrow)
            img?.setBounds(0, 0, 60, 60)
            binding.categoryTitle.setCompoundDrawablesRelative(null, null, img, null)

        }

        binding.categoryTitle.setOnClickListener {
            var img = ContextCompat.getDrawable(requireContext(), R.drawable.ic_left_arrow)
            if (!binding.categoryContainer.isVisible) {
                img?.setBounds(0, 0, 60, 60)
                binding.categoryTitle.setCompoundDrawablesRelative(null, null, img, null)
                //                binding.categoryContainer.slideDown()
                binding.categoryContainer.visibility = View.VISIBLE
                return@setOnClickListener
            }

            binding.categoryContainer.visibility = View.GONE
            img = ContextCompat.getDrawable(requireContext(), R.drawable.ic_down_arrow)
            img?.setBounds(0, 0, 60, 60)
            binding.categoryTitle.setCompoundDrawablesRelative(null, null, img, null)
        }
        binding.categoryTitle.callOnClick()
        categoryAdapter.onClick = object : OnRecyclerItemClick {
            override fun onItemClick(item: Any) {
                item as CategoryItem
                binding.categoryTitle.text = item.title
                binding.categoryTitle.callOnClick()

                //this
                sharedModel.updateDataByCategoryId(item.id)
                // or this
                sharedModel.updateDataByCategoryName(item.title)

                (requireActivity() as MainActivity).navController.navigate(R.id.filterByCategoryFragment)

            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}