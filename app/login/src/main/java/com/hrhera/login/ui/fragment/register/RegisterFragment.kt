package com.hrhera.login.ui.fragment.register

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.shopping.R
import com.example.shopping.databinding.FragmentRegisterBinding
import com.google.android.material.snackbar.Snackbar
import com.hrhera.login.model.data.Data
import com.hrhera.login.model.remote.ShoppingAPI
import com.hrhera.login.ui.framgent.login.LoginViewModel
import com.hrhera.login.utils.Constants
import com.hrhera.login.utils.Constants.Companion.SHOPPING_DATA
import com.hrhera.login.utils.Static
import kotlinx.coroutines.flow.callbackFlow


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
            if (checkingData()) {
                initRegister()
            }
        }
        initLiveDataObserve(view)
    }


    private fun initLiveDataObserve(view: View) {
        registerViewModel.registerUserLiveData.observe(viewLifecycleOwner, {
            if (it != null && it.status) {
                val tokenRegister: String = it.data.token.toString()
                saveData(tokenRegister, true)
                initProgressBar(false)
                Static.onLogin?.onDone()
            } else {
                if (it != null) {
                    initProgressBar(false)
                    Snackbar.make(view, it.message, Snackbar.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun saveData(token: String, status: Boolean) {
        val sharedPreferences = requireActivity().applicationContext.getSharedPreferences(
            SHOPPING_DATA ,
            Context.MODE_PRIVATE
        )
        val editor = sharedPreferences.edit()
        editor.apply {
            putString(Constants.TOKEN_REGISTER, token)
            putBoolean(Constants.STATUS_REGISTER, status)
        }.apply()
    }

    private fun initRegister() {
        initProgressBar(true)
        registerViewModel.registerUser(
            Data(
                binding.registrationEmail.text.toString(),
                binding.registrationUsername.text.toString(),
                binding.registrationPhone.text.toString(),
                binding.registrationPassword.text.toString()
            )
        )
    }

    private fun checkingData(): Boolean {
        val pass = binding.registrationPassword.text.toString()
        val phone = binding.registrationPhone.text.toString()
        val email = binding.registrationEmail.text.toString()
        val state: Boolean = isValidEmail(email)
        if (state) {
            binding.TextInputLayoutEmail.error = null
        }else{
            binding.TextInputLayoutEmail.error = getString(R.string.Invalid_email)
            return false
        }
        if (phone.length < 11) {
            binding.TextInputLayoutPhone.error = getString(R.string.ErrorPhone)
            return false
        }else{
            binding.TextInputLayoutPhone.error = null
        }
        if (pass.length < 6) {
            binding.TextInputLayoutPassword.error = getString(R.string.ErrorPassword)
            return false
        }else{
            binding.TextInputLayoutPassword.error = null
        }

        return true
    }

    private fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun initProgressBar(state: Boolean) {
        if (state) {
            binding.registrationProgressBar.visibility = View.VISIBLE
        } else {
            binding.registrationProgressBar.visibility = View.GONE
        }
    }
}