package com.hrhera.login.model.remote

import com.hrhera.login.model.data.LoginResponse
import com.example.shopping.model.data.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ShoppingAPI {
    @GET("profile")
    suspend fun getProfile(@Header("Authorization") authorization: String): Response<User>

    @POST("register")
    suspend fun postRegister(@Body data: LoginResponse): Response<User>

    @POST("login")
    suspend fun postLogin(@Body data: LoginResponse): Response<User>

    @POST("logout")
    suspend fun postLogout(@Header("Authorization") authorization: String): Response<User>

}