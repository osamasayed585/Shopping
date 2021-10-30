package com.example.shopping.util.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<dataBinding : ViewBinding, viewModel : ViewModel> : Fragment() {

    protected lateinit var viewModel: viewModel

    protected lateinit var binding: dataBinding



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ):
            View {

        viewModel = ViewModelProvider(this)[getViewModel()]

        binding = getFragmentView()


        return binding.root


    }

    /* ---- create base dataBinding ---- */
    abstract fun getFragmentView(): dataBinding

    /* ---- create base viewModel ---- */
    abstract fun getViewModel(): Class<viewModel>

    /* ---- create short toast ---- */
    fun getShortToast(message: String) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
    }

    /* ---- create long toast ---- */
    fun getLongToast(message: String) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_LONG).show()
    }

    /* ---- create base navigation ---- */
    fun getFindControllerNavigate(action: Int) {
        findNavController().navigate(action)
    }

    /* ---- get navigate with bundle pass simple data  ----- */
    fun getFindControllerNavigateWitBundle(action: Int, key: String, data: Any) {
        val bundle = bundleOf(key to data)
        findNavController().navigate(action, bundle)
    }

    /* ---- get navigate with bundle pass object by Serializable  ----- */
    fun getFindControllerNavigateWitSendObjectByBundle(action: Int, key: String, data: Class<Any>) {
        val bundle = Bundle()
        bundle.putSerializable(key, data)
        findNavController().navigate(action, bundle)
    }

}