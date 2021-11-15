package com.example.shopping.ui.account

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.shopping.model.data.User
import com.example.shopping.model.remote.RemoteBuilder
import com.example.shopping.util.AccountStatus
import com.hrhera.login.model.data.Data
import com.hrhera.login.model.remote.RemoteRepositoryImp
import com.hrhera.login.model.remote.ShoppingAPI
import com.hrhera.login.utils.Constants
import com.hrhera.login.utils.Constants.Companion.TAG
import kotlinx.coroutines.launch

class AccountViewModel(application: Application) : AndroidViewModel(application) {
    val ctx = application

    private var repository: RemoteRepositoryImp

    private var _dataViewModel = MutableLiveData<User>()
    val dataViewModel: LiveData<User> get() = _dataViewModel

    private var _statusAccount = MutableLiveData<AccountStatus>()
    val statusAccount: LiveData<AccountStatus> get() = _statusAccount

    private var _statusProgressBar = MutableLiveData<Boolean>()
    val statusProgressBar: LiveData<Boolean> get() = _statusProgressBar

    init {
        val service = RemoteBuilder.builderLogin().create(ShoppingAPI::class.java)
        repository = RemoteRepositoryImp(service)
    }

    fun getData(authorization: String) = viewModelScope.launch {

        _statusProgressBar.value = true

        val result = repository.getProfile(authorization)

        if (result.body() != null && result.isSuccessful) {

            if (result.body()!!.status) {
                _statusProgressBar.value = false
                _statusAccount.value = AccountStatus.SUCCESS()
                _dataViewModel.value = result.body()
            } else {
                _statusProgressBar.value = false
                _statusAccount.value = AccountStatus.SOME_ERROR(result.body()!!.message)
            }
        } else {
            _statusProgressBar.value = false
            _statusAccount.value = AccountStatus.SOME_ERROR(result.message())
            Log.i(TAG, "getData: ${result.message()}")
        }
    }

    fun getUserInformation(){
        val sharedPreferences = ctx.getSharedPreferences(
            Constants.SHOPPING_DATA,
            Context.MODE_PRIVATE
        )
        val isLogin: Boolean = sharedPreferences.getBoolean(Constants.STATUS_LOGIN, false)
        val isRegister: Boolean = sharedPreferences.getBoolean(Constants.STATUS_REGISTER, false)

        val loginToken = sharedPreferences.getString(Constants.TOKEN_LOGIN, null)
        val registerToken = sharedPreferences.getString(Constants.TOKEN_LOGIN, null)

        when {
            isLogin -> getData(loginToken!!)
            isRegister -> getData(registerToken!!)
            else -> {
                _statusAccount.value = AccountStatus.SOME_ERROR("Failed")
            }
        }
    }


}