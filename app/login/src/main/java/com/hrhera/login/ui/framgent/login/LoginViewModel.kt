package com.hrhera.login.ui.framgent.login

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.example.shopping.model.data.User
import com.example.shopping.model.remote.RemoteBuilder
import com.hrhera.login.model.data.Data
import com.hrhera.login.model.remote.RemoteRepositoryImp
import com.hrhera.login.model.remote.ShoppingAPI
import com.hrhera.login.utils.Constants
import com.hrhera.login.utils.Constants.Companion.TAG
import kotlinx.coroutines.launch

class LoginViewModel(application: Application): AndroidViewModel(application) {

    private var repository: RemoteRepositoryImp

    private var loginUserMutableLiveData = MutableLiveData<User>()
    val loginUserLiveData: LiveData<User> get() = loginUserMutableLiveData

    init {
        val service = RemoteBuilder.builderLogin().create(ShoppingAPI::class.java)
        repository = RemoteRepositoryImp(service)
    }

    fun loginUser(responseData: Data) = viewModelScope.launch {
        val result = repository.postLogin(responseData)
        if (result.body() != null && result.isSuccessful){
            loginUserMutableLiveData.postValue(result.body())
        }else{
            Log.i(TAG, "loginUser: ${result.message()}")
        }
    }
}