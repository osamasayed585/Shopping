package com.hrhera.login.model.remote

import com.example.shopping.model.remote.RemoteRepository
import com.hrhera.login.model.data.Data
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteRepositoryImp(private val api: ShoppingAPI): RemoteRepository {
    override suspend fun getProfile(authorization: String) = withContext(Dispatchers.IO){
        api.getProfile(authorization)
    }
    override suspend fun postRegister(responseData: Data)= withContext(Dispatchers.IO){
        api.postRegister(responseData)
    }

    override suspend fun postLogin(responseData: Data) = withContext(Dispatchers.IO){
        api.postLogin(responseData)
    }

    override suspend fun postLogout(authorization: String) = withContext(Dispatchers.IO){
        api.postLogout(authorization)
    }
}