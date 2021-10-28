package com.example.shopping.model.repository

import com.example.shopping.model.data_class.CategoryItem
import retrofit2.Response
import java.util.*

object TestData {

    fun getCategory(): Response<List<CategoryItem>> {
        return Response.success( arrayListOf(
            CategoryItem("Toy","https://cdn0.iconfinder.com/data/icons/leto-devices-2/64/tamagotchi_toy-512.png","1"),
            CategoryItem("Toy2","https://e7.pngegg.com/pngimages/1023/885/png-clipart-toy-graphy-illustration-i-lovely-toy-box-food-photography-thumbnail.png","2"),
            CategoryItem("Small","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSVypCoaL1R2Q3k-F3tLIODdtHNayLZCi4QqQ&usqp=CAU","3"),
            CategoryItem("Gift","https://png.pngtree.com/element_our/20190601/ourmid/pngtree-cartoon-toy-icon-free-illustration-image_1345995.jpg","4"),
            CategoryItem("GiftS","https://cdn0.iconfinder.com/data/icons/toy-10/64/music_box-multimedia-sound_box-toy-512.png","5"),
            CategoryItem("Test Title","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS2IIyOeE1Lh76aTtBgvYugQYMkIJsE67_cOw&usqp=CAU","6"),
            CategoryItem("Gift","https://d3tvemk8zf61cc.cloudfront.net/homepage/pop_pngs/hp-brain-icon.png","7"),
            CategoryItem("Gift12","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQrWfzcHF0VgAr45E6NybfTgk7bv5lhWj9nfw&usqp=CAU","8"),
            CategoryItem("Gift15","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ8QSctRqJGwMhp7OFxzhi6BRGAJcd3pc7_fg&usqp=CAU","9"),
            CategoryItem("Gift20","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQDF_ODi5M11Gc5vGzOmAmGUOt-u-X1sk-dVw&usqp=CAU","10"),
            CategoryItem("Gift30","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwN69NsmE7ZqhZ0i3M8Yuco_h5ZEeQwaJzkA&usqp=CAU","11"),

        ));

    }

}