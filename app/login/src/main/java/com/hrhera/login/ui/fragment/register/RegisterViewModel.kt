package com.hrhera.login.ui.fragment.register

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.shopping.R
import com.example.shopping.model.remote.RemoteBuilder
import com.hrhera.login.model.data.Data
import com.hrhera.login.model.remote.RemoteRepositoryImp
import com.hrhera.login.model.remote.ShoppingAPI
import com.hrhera.login.utils.*
import com.hrhera.login.utils.Constants.Companion.TAG
import kotlinx.coroutines.launch

class RegisterViewModel(application: Application): AndroidViewModel(application) {
    private val ctx = application

    private var repository: RemoteRepositoryImp

    private var registerUserMutableLiveData = MutableLiveData<RegisterState>()
    val registerUserLiveData: LiveData<RegisterState> get() = registerUserMutableLiveData

    init {
        val service = RemoteBuilder.builderLogin().create(ShoppingAPI::class.java)
        repository = RemoteRepositoryImp(service)
    }

    fun registerUser(responseData: Data){
        if (responseData.email.isInvalidEmail()){
            registerUserMutableLiveData.value =
                RegisterState.EMAIL_ERROR(ctx.getString(R.string.Invalid_email))
            return
        }
        if (responseData.password.isInvalidPassword()){
            registerUserMutableLiveData.value =
                RegisterState.PASSWORD_ERROR(ctx.getString(R.string.Invalid_password))
            return
        }
        if (responseData.phone.isInvalidPhone()){
            registerUserMutableLiveData.value =
                RegisterState.PHONE_ERROR(ctx.getString(R.string.ErrorPhone))
            return
        }
        registerUserMutableLiveData.value = RegisterState.LOADING()
        startRegister(responseData)
    }

    private fun startRegister(responseData: Data) = viewModelScope.launch {
        val result = repository.postRegister(responseData)
        if (result.body() != null && result.isSuccessful){
            if (result.body()!!.status){
                registerUserMutableLiveData.value = RegisterState.SUCCESS()
                saveData(result.body()!!.data.token.toString(), result.body()!!.status)
            }else{
                registerUserMutableLiveData.value = RegisterState.SOME_ERROR(result.body()!!.message)
            }
        }else{
            registerUserMutableLiveData.value = RegisterState.SOME_ERROR(result.message())
            Log.i(TAG, "startRegister: ${result.message()}")
        }
    }
    private fun saveData(token: String, status: Boolean) {
        val sharedPreferences = ctx.getSharedPreferences(
            Constants.SHOPPING_DATA,
            Context.MODE_PRIVATE
        )
        val editor = sharedPreferences.edit()
        editor.apply {
            putString(Constants.TOKEN_REGISTER, token)
            putBoolean(Constants.STATUS_REGISTER, status)
        }.apply()
        Static.onUserLogin?.onLogin()
    }
}