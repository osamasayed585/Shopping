package com.example.shopping.model.data_class

import com.google.gson.annotations.SerializedName

data class ProductItem (

    @SerializedName("title") var title : String="",
    @SerializedName("price") var price : String="",
    @SerializedName("category") var category : String="",
    @SerializedName("categoryId") var categoryId : String="",
    @SerializedName("salesCount") var salesCount : String="",
    @SerializedName("rating") var rating : Int=0,
    @SerializedName("barcode") var barcode : String="",
    @SerializedName("image1") var image1 : String="",
    @SerializedName("image2") var image2 : String="",
    @SerializedName("image3") var image3 : String="",
    @SerializedName("id") var id : String=""

        )