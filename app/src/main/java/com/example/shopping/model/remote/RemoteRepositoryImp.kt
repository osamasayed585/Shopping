package com.example.shopping.model.remote

import com.example.shopping.model.data.Data
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteRepositoryImp(val api:ShoppingAPI): RemoteRepository{
    override suspend fun getProfile(authorization: String) = withContext(Dispatchers.IO){
        api.getProfile(authorization)
    }
    override suspend fun postRegister(data: Data)= withContext(Dispatchers.IO){
        api.postRegister(data)
    }

    override suspend fun postLogin(data: Data) = withContext(Dispatchers.IO){
        api.postLogin(data)
    }

    override suspend fun postLogout(authorization: String) = withContext(Dispatchers.IO){
        api.postLogout(authorization)
    }
}