package com.hrhera.login.ui.fragment.register

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.shopping.model.data.User
import com.example.shopping.model.remote.RemoteBuilder
import com.hrhera.login.model.data.Data
import com.hrhera.login.model.remote.RemoteRepositoryImp
import com.hrhera.login.model.remote.ShoppingAPI
import com.hrhera.login.utils.Constants.Companion.TAG
import kotlinx.coroutines.launch

class RegisterViewModel(application: Application): AndroidViewModel(application) {

    private var repository: RemoteRepositoryImp

    private var registerUserMutableLiveData = MutableLiveData<User>()
    val registerUserLiveData: LiveData<User> get() = registerUserMutableLiveData

    init {
        val service = RemoteBuilder.builderLogin().create(ShoppingAPI::class.java)
        repository = RemoteRepositoryImp(service)
    }

    fun registerUser(responseData: Data) = viewModelScope.launch {
        val result = repository.postRegister(responseData)
        if (result.body() != null && result.isSuccessful){
            registerUserMutableLiveData.postValue(result.body())
        }else{
            Log.i(TAG, "loginUser: ${result.message()}")
        }
    }
}