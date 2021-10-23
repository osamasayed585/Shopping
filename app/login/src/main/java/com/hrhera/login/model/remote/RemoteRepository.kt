package com.example.shopping.model.remote

import com.hrhera.login.model.data.LoginResponse
import com.example.shopping.model.data.User
import retrofit2.Response

interface RemoteRepository {

    suspend fun getProfile(authorization: String): Response<User>

    suspend fun postRegister(data: LoginResponse): Response<User>

    suspend fun postLogin(data: LoginResponse): Response<User>

    suspend fun postLogout(authorization: String): Response<User>
}