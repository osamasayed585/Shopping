package com.example.shopping.model.remote

import com.hrhera.login.utils.Constants.Companion.BOOKS_BASE_URL
import com.hrhera.login.utils.Constants.Companion.LOGIN_BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteBuilder {

    companion object{
        fun builderLogin(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(LOGIN_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        fun builderBooks(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BOOKS_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}