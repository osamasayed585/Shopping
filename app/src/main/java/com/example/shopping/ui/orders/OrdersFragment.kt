package com.example.shopping.ui.orders

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopping.databinding.FragmentOrdersBinding
import com.example.shopping.model.data_class.CartItem
import com.example.shopping.ui.adapter.OrderRecyclerAdapter
import com.example.shopping.util.call_back.OnRecyclerItemClick
import com.example.shopping.util.view.BaseFragment


class OrdersFragment : BaseFragment<FragmentOrdersBinding, OrderViewModel>() {

    override fun getFragmentView() = FragmentOrdersBinding.inflate(layoutInflater)

    override fun getViewModel() = OrderViewModel::class.java


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = OrderRecyclerAdapter()
        binding.showOrders.layoutManager = LinearLayoutManager(requireContext())
        binding.showOrders.adapter = adapter

        adapter.onAddOneClick = object : OnRecyclerItemClick {
            override fun onItemClick(item: Any) {
                viewModel.addOne(item as CartItem)
            }
        }

        adapter.onRemoveFullItemClick = object : OnRecyclerItemClick {
            override fun onItemClick(item: Any) {
                viewModel.removeFullItem(item as CartItem)

            }
        }

        adapter.onRemoveOneClick = object : OnRecyclerItemClick {
            override fun onItemClick(item: Any) {
                viewModel.removeOne(item as CartItem)
            }
        }


        viewModel.orderCartItemLiveData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        viewModel.orderCartItemTotalLiveData.observe(viewLifecycleOwner) {
            binding.total.text=it
        }

    }




}