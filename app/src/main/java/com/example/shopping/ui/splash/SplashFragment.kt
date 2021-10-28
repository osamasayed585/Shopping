package com.example.shopping.ui.splash


import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.shopping.R
import com.example.shopping.databinding.FragmentSplashBinding
import com.example.shopping.ui.main.MainActivity
import com.hrhera.login.utils.AnimationUtil.scaleUp
import com.hrhera.login.utils.Constants.Companion.SHOPPING_DATA
import com.hrhera.login.utils.Static
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        initStatusBar(false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).supportActionBar?.hide()
        toWhere()
        binding.btLogin.setOnClickListener { findNavController().navigate(R.id.action_splashFragment_to_loginFragment) }
        binding.btSignUp.setOnClickListener { findNavController().navigate(R.id.action_splashFragment_to_registerFragment) }
        binding.skipLogin.setOnClickListener { Static.onLogin?.onDone() }

    }


    private fun toWhere() {

        initButtons()

        val sharedPreferences = requireActivity().applicationContext.getSharedPreferences(
            SHOPPING_DATA,
            Context.MODE_PRIVATE
        )
        val statusLogin = sharedPreferences.getBoolean("STATUS_LOGIN", false)
        val statusRegister = sharedPreferences.getBoolean("STATUS_REGISTER", false)
        if (statusLogin || statusRegister) {
            Handler(Looper.getMainLooper()).postDelayed({
                Static.onLogin?.onDone()
            }, 2000)

            return
        }
        initStatusBar(true)
        GlobalScope.launch {
            delay(100)
            GlobalScope.launch(Dispatchers.Main) {
                binding.btLogin.scaleUp(1500)
                binding.btSignUp.scaleUp(1500)
                binding.skipLogin.scaleUp(1500)

            }
        }
    }

    private fun initButtons() {
        binding.btLogin.isVisible = false
        binding.btSignUp.isVisible = false
        binding.skipLogin.isVisible = false
    }

    private fun initStatusBar(state: Boolean) {
        if (state) {
            showSystemUI()

        } else {
            hideSystemUI()
        }
    }


    private fun hideSystemUI() {
        WindowCompat.setDecorFitsSystemWindows(requireActivity().window, false)
        WindowInsetsControllerCompat(
            requireActivity().window,
            binding.root
        ).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

    private fun showSystemUI() {
        WindowCompat.setDecorFitsSystemWindows(requireActivity().window, true)
        WindowInsetsControllerCompat(
            requireActivity().window,
            binding.root
        ).show(WindowInsetsCompat.Type.systemBars())
    }
}