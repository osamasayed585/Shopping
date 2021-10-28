package com.example.shopping.model.data_class

import com.google.gson.annotations.SerializedName

data class CategoryItem(

    @SerializedName("title") var title: String = "",
    @SerializedName("price") var image: String = "",
    @SerializedName("id") var id: String = ""
)