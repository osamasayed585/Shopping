package com.example.shopping.model.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteBuilder {

    companion object{
        private const val LOGIN_BASE_URL: String = "https://student.valuxapps.com/api/"
        private const val BOOKS_BASE_URL: String = "https://61562057e039a0001725a91d.mockapi.io/book/v1/"

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