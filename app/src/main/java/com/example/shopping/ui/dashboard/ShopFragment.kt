package com.example.shopping.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.shopping.R
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.search -> {
                    findNavController().navigate(R.id.action_navigation_shopping_cart_to_searchFragment)
                     true
                }
                R.id.favourite -> {
                    Toast.makeText(context, "favourite", Toast.LENGTH_SHORT).show()
                     true
                }
                R.id.order -> {
                    Toast.makeText(context, "order", Toast.LENGTH_SHORT).show()
                     true
                }
                else -> {
                     false
                }
            }
        }
    }

    /*
    *
    *  Why these functions are not working
    *
    * is there something wrong.??
    * */


//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.home_and_shop_menu,menu)
//        super.onCreateOptionsMenu(menu, inflater)
//    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when(item.itemId){
//            R.id.search -> {
//                Toast.makeText(context, "search", Toast.LENGTH_SHORT).show()
//                return true
//            }
//            R.id.favourite -> {
//                Toast.makeText(context, "favourite", Toast.LENGTH_SHORT).show()
//                return true
//            }
//            R.id.order -> {
//                Toast.makeText(context, "order", Toast.LENGTH_SHORT).show()
//                return true
//            }
//            else -> {
//                return false
//            }
//        }
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}