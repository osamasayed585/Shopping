package com.hrhera.login.ui.fragment.register

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.shopping.R
import com.example.shopping.databinding.FragmentRegisterBinding
import com.google.android.material.snackbar.Snackbar
import com.hrhera.login.model.data.Data
import com.hrhera.login.utils.Constants
import com.hrhera.login.utils.Constants.Companion.SHOPPING_DATA
import com.hrhera.login.utils.RegisterState
import com.hrhera.login.utils.Static


class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerViewModel = ViewModelProvider(requireActivity()).get(RegisterViewModel::class.java)
        binding.registerLoginHere.setOnClickListener {
            Navigation.findNavController(view).popBackStack()
        }

        binding.registrationRegistration.setOnClickListener {
            initRegister()
        }

        registerViewModel.registerUserLiveData.observe(viewLifecycleOwner,{
            initProgressBar(it == RegisterState.LOADING())

            when(it) {
                is RegisterState.EMAIL_ERROR -> {
                    binding.TextInputLayoutEmail.error = it.error
                }
                is RegisterState.PHONE_ERROR -> {
                    binding.TextInputLayoutPhone.error = it.error
                }
                is RegisterState.PASSWORD_ERROR -> {
                    binding.TextInputLayoutPassword.error = it.error
                }
                is RegisterState.SOME_ERROR -> {
                    binding.errorMessage.visibility = VISIBLE
                    binding.errorMessage.text = it.error
                }
                else -> {
                    initProgressBar( it != RegisterState.SUCCESS())
                }
            }
        })

    }

    private fun initRegister() {
        binding.TextInputLayoutEmail.error = null
        binding.TextInputLayoutPhone.error = null
        binding.TextInputLayoutPassword.error = null
        binding.errorMessage.visibility = GONE

        registerViewModel.registerUser(
            Data(
                binding.registrationEmail.text.toString(),
                binding.registrationUsername.text.toString(),
                binding.registrationPhone.text.toString(),
                binding.registrationPassword.text.toString()
            )
        )
    }

    private fun initProgressBar(state: Boolean) {
        if (state) {
            binding.registrationProgressBar.visibility = VISIBLE
        } else {
            binding.registrationProgressBar.visibility = GONE
        }
    }
}