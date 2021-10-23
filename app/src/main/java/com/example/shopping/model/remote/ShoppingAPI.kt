package com.example.shopping.model.remote

import com.example.shopping.model.data.Data
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
    suspend fun postRegister(@Body data: Data): Response<User>

    @POST("login")
    suspend fun postLogin(@Body data: Data): Response<User>

    @POST("logout")
    suspend fun postLogout(@Header("Authorization") authorization: String): Response<User>

}