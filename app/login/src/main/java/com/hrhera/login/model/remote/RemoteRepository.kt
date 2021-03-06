package com.example.shopping.model.remote

import com.example.shopping.model.data.User
import com.hrhera.login.model.data.Data
import retrofit2.Response

interface RemoteRepository {

    suspend fun getProfile(authorization: String): Response<User>

    suspend fun postRegister(data: Data): Response<User>

    suspend fun postLogin(data: Data): Response<User>

    suspend fun postLogout(authorization: String): Response<User>
}