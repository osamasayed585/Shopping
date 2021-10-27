package com.example.shopping.ui.account

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shopping.util.AccountStates

class AccountViewModel(application: Application) : AndroidViewModel(application) {
    private val ctx = application

    private val _accountMutable = MutableLiveData<AccountStates>()
    val accountLiveData: LiveData<AccountStates> get() = _accountMutable


    fun checkingItemId(itemId: Int){

    }

}