package com.hrhera.login.model.remote

import com.example.shopping.model.remote.RemoteRepository
import com.hrhera.login.model.data.LoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteRepositoryImp(val api: ShoppingAPI): RemoteRepository {
    override suspend fun getProfile(authorization: String) = withContext(Dispatchers.IO){
        api.getProfile(authorization)
    }
    override suspend fun postRegister(data: LoginResponse)= withContext(Dispatchers.IO){
        api.postRegister(data)
    }

    override suspend fun postLogin(data: LoginResponse) = withContext(Dispatchers.IO){
        api.postLogin(data)
    }

    override suspend fun postLogout(authorization: String) = withContext(Dispatchers.IO){
        api.postLogout(authorization)
    }
}