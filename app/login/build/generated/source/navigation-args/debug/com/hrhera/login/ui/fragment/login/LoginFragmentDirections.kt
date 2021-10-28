package com.hrhera.login.ui.fragment.login

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.shopping.R

public class LoginFragmentDirections private constructor() {
  public companion object {
    public fun actionLoginFragmentToRegisterFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_loginFragment_to_registerFragment)
  }
}
