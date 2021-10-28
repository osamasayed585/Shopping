package com.example.shopping.ui.splash

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.shopping.R

public class SplashFragmentDirections private constructor() {
  public companion object {
    public fun actionSplashFragmentToLoginFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_splashFragment_to_loginFragment)

    public fun actionSplashFragmentToRegisterFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_splashFragment_to_registerFragment)
  }
}
