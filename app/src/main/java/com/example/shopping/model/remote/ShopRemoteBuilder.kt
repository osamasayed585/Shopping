package com.example.shopping.model.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ShopRemoteBuilder {

    companion object{
        private const val PRODUCTS_BASE_URL: String = "https://61562057e039a0001725a91d.mockapi.io/book/v1/"
        fun productBuilder(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(PRODUCTS_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}