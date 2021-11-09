package com.example.shopping.ui.profile

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.shopping.R
import com.example.shopping.databinding.FragmentProfileBinding
import com.example.shopping.ui.main.MainActivity
import com.example.shopping.util.initToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import com.hrhera.login.utils.AnimationUtil.slideDown
import com.hrhera.login.utils.Static
import kotlinx.coroutines.delay

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]

        viewModel.initStatus()

        viewModel.mutableProgressBar.observe(viewLifecycleOwner, {
            initProgressBar(it)
        })

        viewModel.topMessageError.observe(viewLifecycleOwner,{
            initTopErrorMessage(it)
        })

        viewModel.mutableStatus.observe(viewLifecycleOwner, {
            binding.profileTextErrorMessage.text = it
            binding.profileTextLogin.setOnClickListener {
                Static.onUserLogin?.onLogOut()
            }
        })

        viewModel.liveData.observe(viewLifecycleOwner, {
            binding.profileName.text = it.data.name
        })

        binding.profileAccount.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_profile_to_accountFragment)
        }
        binding.profileLanguage.setOnClickListener {
            // todo
        }
        binding.profilePassword.setOnClickListener {
            // todo
        }
        binding.profileFavourite.setOnClickListener {
            // todo
        }
        binding.profileAbout.setOnClickListener {
            // todo
        }
        binding.profileLogout.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_profile_to_loginFragment2)
            Static.onUserLogin?.onLogOut()
        }

//        initToolbar(binding.toolbar, R.string.account, false)
    }

    fun initProgressBar(status: Boolean) {
        binding.profileProgressBar.isVisible = status
    }
     fun initTopErrorMessage(status: Boolean){
        binding.profileMessageToast.isVisible = status
        binding.profileMessageToast.slideDown(900)
    }
}
