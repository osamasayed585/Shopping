package com.hrhera.login.ui.fragment.login

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.example.shopping.R
import com.example.shopping.model.remote.RemoteBuilder
import com.hrhera.login.model.data.Data
import com.hrhera.login.model.remote.RemoteRepositoryImp
import com.hrhera.login.model.remote.ShoppingAPI
import com.hrhera.login.utils.*
import com.hrhera.login.utils.Constants.Companion.TAG
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val ctx = application
    /*
    * Checking data validation must be in viewModel
    * View is just response for showing view and data no business logic in view
    * */


    private var repository: RemoteRepositoryImp
    private  var loginStatesMutableLiveData = MutableLiveData<LoginStates>()

    val loginUserLiveData: LiveData<LoginStates> get() = loginStatesMutableLiveData

    init {
        val service = RemoteBuilder.builderLogin().create(ShoppingAPI::class.java)
        repository = RemoteRepositoryImp(service)
    }

    fun loginUser(responseData: Data) {

        if (responseData.email.isInvalidEmail()) {
            loginStatesMutableLiveData.value =
                LoginStates.EMAIL_ERROR(ctx.getString(R.string.Invalid_email))
            return
        }
        if (responseData.password.isInvalidPassword()) {
            loginStatesMutableLiveData.value =
                LoginStates.PASSWORD_ERROR(ctx.getString(R.string.Invalid_password))
            return
        }
        loginStatesMutableLiveData.value = LoginStates.LOADING()
        startLogin(responseData)

    }


    private fun startLogin(responseData: Data) = viewModelScope.launch {

        val result = repository.postLogin(responseData)

        if (result.body() != null && result.isSuccessful) {
            if (result.body()!!.status) {
                loginStatesMutableLiveData.value = LoginStates.SUCCESS()
                saveData(result.body()!!.data.token.toString(), result.body()!!.status)
            } else {
                loginStatesMutableLiveData.value = LoginStates.SOME_ERROR(result.body()!!.message)
            }
        } else {
            loginStatesMutableLiveData.value = LoginStates.SOME_ERROR(result.message())
            Log.i(TAG, "startLogin: ${result.message()}")
        }
    }


    private fun saveData(token: String, status: Boolean) {
        val sharedPreferences = ctx.getSharedPreferences(
            Constants.SHOPPING_DATA,
            Context.MODE_PRIVATE
        )
        val editor = sharedPreferences.edit()
        editor.apply {
            putString(Constants.TOKEN_LOGIN, token)
            putBoolean(Constants.STATUS_LOGIN, status)
        }.apply()
        Static.onUserLogin?.onLogin()
    }
}