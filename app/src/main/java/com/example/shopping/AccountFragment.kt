package com.example.shopping

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shopping.databinding.FragmentAccountBinding

class AccountFragment : Fragment() {

    private lateinit var binding: FragmentAccountBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentAccountBinding.inflate(inflater,container,false)
        statusEditText(false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.accountUpdate.setOnClickListener {
            binding.accountUpdate.text = "Done"
            statusEditText(true)
        }
        binding.accountName.setText("Osama")
        binding.accountEmail.setText("osamasayed151@github.com")
        binding.accountPhone.setText("01286362539")
    }

    private fun statusEditText(status: Boolean) {
        binding.accountName.isFocusable = status
        binding.accountName.isFocusableInTouchMode = status
        binding.accountEmail.isFocusable = status
        binding.accountEmail.isFocusableInTouchMode = status
        binding.accountPhone.isFocusable = status
        binding.accountPhone.isFocusableInTouchMode = status
    }
}