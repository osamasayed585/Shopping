package com.hrhera.login.ui.framgent.login


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.shopping.R
import com.example.shopping.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar
import com.hrhera.login.model.data.Data
import com.hrhera.login.utils.Constants.Companion.SHOPPING_DATA
import com.hrhera.login.utils.Constants.Companion.STATUS_LOGIN
import com.hrhera.login.utils.Constants.Companion.TOKEN_LOGIN
import com.hrhera.login.utils.Static


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var loginViewModel: LoginViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginViewModel = ViewModelProvider(requireActivity()).get(LoginViewModel::class.java)
        // if you check text register
        binding.loginRegistration.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
        // if you check Login button
        binding.btnLogin.setOnClickListener {
            initLogin()
        }
        initLiveDataObserve(view)
    }


    private fun initLiveDataObserve(view: View) {
        loginViewModel.loginUserLiveData.observe(viewLifecycleOwner, {
            if (it != null && it.status) {
                val tokenLogin: String = it.data.token.toString()
                saveData(tokenLogin, true)
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
            SHOPPING_DATA,
            Context.MODE_PRIVATE
        )
        val editor = sharedPreferences.edit()
        editor.apply {
            putString(TOKEN_LOGIN, token)
            putBoolean(STATUS_LOGIN, status)
        }.apply()
    }

    private fun initLogin() {
        initProgressBar(true)
        loginViewModel.loginUser(
            Data(
                binding.loginEmail.text.toString(),
                binding.loginPassword.text.toString()
            )
        )
    }
    private fun initProgressBar(state: Boolean) {
        if (state) {
            binding.loginProgressBar.visibility = View.VISIBLE
        } else {
            binding.loginProgressBar.visibility = View.GONE
        }
    }
}