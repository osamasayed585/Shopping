package com.hrhera.login.model.remote

import com.example.shopping.model.data.User
import com.hrhera.login.model.data.Data
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ShoppingAPI {
    @GET("profile")
    suspend fun getProfile(@Header("Authorization") authorization: String): Response<User>

    @POST("register")
    suspend fun postRegister(@Body responseData: Data): Response<User>

    @POST("login")
    suspend fun postLogin(@Body responseData: Data): Response<User>

    @POST("logout")
    suspend fun postLogout(@Header("Authorization") authorization: String): Response<User>

}