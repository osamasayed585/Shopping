package com.example.shopping.ui.splash


import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.shopping.R
import com.example.shopping.databinding.FragmentSplashBinding
import com.example.shopping.ui.main.MainActivity
import com.hrhera.login.utils.Constants.Companion.SHOPPING_DATA
import com.hrhera.login.utils.Static

class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initStatusBar(false)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).supportActionBar?.hide()
        toWhere()
        initButtons(false)
        binding.btLogin.setOnClickListener { findNavController().navigate(R.id.action_splashFragment_to_loginFragment) }
        binding.btSignUp.setOnClickListener { findNavController().navigate(R.id.action_splashFragment_to_registerFragment) }
        binding.skipLogin.setOnClickListener { Static.onLogin?.onDone() }

    }


    private fun toWhere() {
        val sharedPreferences = requireActivity().applicationContext.getSharedPreferences(
            SHOPPING_DATA,
            Context.MODE_PRIVATE
        )
        val statusLogin = sharedPreferences.getBoolean("STATUS_LOGIN", false)
        val statusRegister = sharedPreferences.getBoolean("STATUS_REGISTER", false)

        Handler(Looper.getMainLooper()).postDelayed({
            if (statusLogin || statusRegister) {
                Static.onLogin?.onDone()
            }
            initButtons(true)
            initStatusBar(true)
        }, 3000)
    }

    private fun initButtons(state: Boolean) {
        if (state) {
            binding.skipLogin.visibility = View.VISIBLE
            binding.btLogin.visibility = View.VISIBLE
            binding.btSignUp.visibility = View.VISIBLE
        }else{
            binding.skipLogin.visibility = View.GONE
            binding.btLogin.visibility = View.GONE
            binding.btSignUp.visibility = View.GONE
        }
    }

    private fun initStatusBar(state: Boolean) {
        if (state) {
            (requireActivity() as MainActivity).supportActionBar?.show()
        } else {
            requireActivity().window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        }
    }
}