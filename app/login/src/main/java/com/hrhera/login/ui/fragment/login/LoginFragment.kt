package com.hrhera.login.ui.fragment.login


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.shopping.R
import com.example.shopping.databinding.FragmentLoginBinding
import com.hrhera.login.model.data.Data
import com.hrhera.login.utils.AnimationUtil.slideDown
import com.hrhera.login.utils.AnimationUtil.slideUp
import com.hrhera.login.utils.LoginStates
import kotlinx.coroutines.*


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
            handleOnLoginClick()
        }


        loginViewModel.loginUserLiveData.observe(viewLifecycleOwner) {
            initProgressBar(it == LoginStates.LOADING())

            when (it) {
                is LoginStates.EMAIL_ERROR -> {
                    binding.emailTextInputLayout.error = it.error
                }
                is LoginStates.PASSWORD_ERROR -> {
                    binding.passwordTextInputLayout.error = it.error
                }
                is LoginStates.SOME_ERROR -> {
                    binding.errorMessage.visibility = VISIBLE
                    binding.errorMessage.text = it.error
                }
                else -> {
                    initProgressBar(it != LoginStates.SUCCESS())
                }
            }
        }


        initViewAnimation()


    }


    @DelicateCoroutinesApi
    private fun initViewAnimation() {
        binding.bottomCard.visibility = View.INVISIBLE
        binding.tvLogo.visibility = View.INVISIBLE
        binding.imageView.visibility = View.INVISIBLE
        GlobalScope.launch {
            delay(100)
            GlobalScope.launch(Dispatchers.Main) {
                binding.bottomCard.slideUp(800)
            }

            GlobalScope.launch(Dispatchers.Main) {
                binding.imageView.slideDown(800)
            }
            delay(300)
            GlobalScope.launch(Dispatchers.Main) {
                binding.tvLogo.slideDown(900)
            }
        }
    }


    private fun handleOnLoginClick() {
        binding.passwordTextInputLayout.error = null
        binding.emailTextInputLayout.error = null
        binding.errorMessage.visibility = GONE
        loginViewModel.loginUser(
            Data(
                binding.loginEmail.text.toString(),
                binding.loginPassword.text.toString()
            )
        )
    }

    private fun initProgressBar(state: Boolean) {
        if (state) {
            binding.loginProgressBar.visibility = VISIBLE
        } else {
            binding.loginProgressBar.visibility = GONE
        }
    }


}