package com.example.shopping.ui.profile

import android.app.Application
import android.content.Context
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.shopping.R
import com.example.shopping.model.data.User
import com.example.shopping.model.data_class.ProductItem
import com.example.shopping.model.remote.ShopRemoteBuilder
import com.example.shopping.model.repository.ProductsRemoteRepositoryImp
import com.google.android.material.snackbar.Snackbar
import com.hrhera.login.utils.Constants
import com.hrhera.login.utils.Constants.Companion.STATUS_LOGIN
import com.hrhera.login.utils.Constants.Companion.STATUS_REGISTER
import com.hrhera.login.utils.Constants.Companion.TAG
import com.hrhera.login.utils.Constants.Companion.TOKEN_LOGIN
import com.hrhera.login.utils.Constants.Companion.TOKEN_REGISTER
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ProfileViewModel(application: Application) : AndroidViewModel(application) {
    private val ctx = application
    private var repository: ProductsRemoteRepositoryImp = ProductsRemoteRepositoryImp()

    private var _mutableLiveData = MutableLiveData<User>()
    val nameLiveData: LiveData<User> = _mutableLiveData

    private var _mutableProgressBar = MutableLiveData<Boolean>()
    val mutableProgressBar: LiveData<Boolean> = _mutableProgressBar

    private var _mutableTopMessageError = MutableLiveData<Boolean>()
    val topMessageError: LiveData<Boolean> = _mutableTopMessageError

    private var _mutableStatus = MutableLiveData<String>()
    val mutableStatus: LiveData<String> = _mutableStatus

    fun getUserData(authorization: String) {
        _mutableProgressBar.value = true
        viewModelScope.launch {
            val result = repository.getProfile(authorization)
            if (result.body() != null && result.isSuccessful) {
                _mutableProgressBar.value = false
                _mutableLiveData.value = result.body()
            } else {
                _mutableProgressBar.value = false
                Log.i(TAG, "getUserData: ${result.message()}")
            }
        }
    }

    fun initStatus() {
        val sharedPreferences = ctx.getSharedPreferences(
            Constants.SHOPPING_DATA,
            Context.MODE_PRIVATE
        )

        val isLogin: Boolean = sharedPreferences.getBoolean(STATUS_LOGIN, false)
        val isRegister: Boolean = sharedPreferences.getBoolean(STATUS_REGISTER, false)

        val tokenRegister = sharedPreferences.getString(TOKEN_REGISTER, null)
        val tokenLogin = sharedPreferences.getString(TOKEN_LOGIN,null)

        when {
            isLogin -> getUserData(tokenLogin!!)
            isRegister -> getUserData(tokenRegister!!)
            else -> {
                _mutableTopMessageError.value = true
                viewModelScope.launch {
                    _mutableStatus.value = "Sorry, you must be login.."
                    delay(4000)
                    _mutableTopMessageError.value = false
                }

            }
        }
    }
}