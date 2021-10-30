package com.example.shopping.model.local

import android.util.Log
import com.example.shopping.model.data_class.CartItem
import com.example.shopping.model.data_class.CategoryItem
import com.example.shopping.model.data_class.ProductItem
import kotlinx.coroutines.delay
import retrofit2.Response
import java.util.*
import java.util.Collections.reverse

object TestData {
    private val listOfCategoryItem = arrayListOf(
        CategoryItem(
            "laptop",
            "https://api.time.com/wp-content/uploads/2017/05/laptops.jpg",
            "1"
        ),
        CategoryItem(
            "cameras",
            "https://3.img-dpreview.com/files/p/E~TS590x401~articles/1395652228/Best-2010s-03_1.jpeg",
            "2"
        ),
        CategoryItem(
            "keyboards",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRABnop9jSquNIRWOMDAX_AgdlNQpyxg0hnIw&usqp=CAU",
            "3"
        ),
        CategoryItem(
            "mouse",
            "https://static1.makeuseofimages.com/wordpress/wp-content/uploads/2016/03/replace-old-mouse.jpg",
            "4"
        ),
        CategoryItem(
            "GiftS",
            "https://cdn0.iconfinder.com/data/icons/toy-10/64/music_box-multimedia-sound_box-toy-512.png",
            "5"
        ),
        CategoryItem(
            "Test Title",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS2IIyOeE1Lh76aTtBgvYugQYMkIJsE67_cOw&usqp=CAU",
            "6"
        ),
        CategoryItem(
            "Gift",
            "https://d3tvemk8zf61cc.cloudfront.net/homepage/pop_pngs/hp-brain-icon.png",
            "7"
        ),
        CategoryItem(
            "Gift12",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQrWfzcHF0VgAr45E6NybfTgk7bv5lhWj9nfw&usqp=CAU",
            "8"
        ),
        CategoryItem(
            "Gift15",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ8QSctRqJGwMhp7OFxzhi6BRGAJcd3pc7_fg&usqp=CAU",
            "9"
        ),
        CategoryItem(
            "Gift20",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQDF_ODi5M11Gc5vGzOmAmGUOt-u-X1sk-dVw&usqp=CAU",
            "10"
        ),
        CategoryItem(
            "Gift30",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwN69NsmE7ZqhZ0i3M8Yuco_h5ZEeQwaJzkA&usqp=CAU",
            "11"
        ),

        )
    private val listOfItems = mutableListOf<ProductItem>()
    private fun loadData() {
        val listOfImage = listOf(
            //laptop
            "https://consumer-img.huawei.com/content/dam/huawei-cbg-site/me-africa/eg-en/mkt/plp/laptops/matebook-x-pro/matebook-x-pro.jpg",
            "https://id-media.apjonlinecdn.com/catalog/product/cache/b3b166914d87ce343d4dc5ec5117b502/2/1/21c1_omen_vanellope_16_60w_nonnumpad_4zone_lcd_micasilver_front_1.png",
            "https://cdn.thewirecutter.com/wp-content/uploads/2020/04/laptops-lowres-2x1-.jpg?auto=webp&quality=75&crop=2:1&width=1024",
            "https://cdn.vox-cdn.com/thumbor/lRwetR_dg8WBLFIUPzY7l0QYCaI=/1400x0/filters:no_upscale()/cdn.vox-cdn.com/uploads/chorus_asset/file/22411713/cfaulkner_20210326_4491_0006.jpg",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTBbxNrQRXPMWqtbKvU9GPL8BGQVhxLjPSaIg&usqp=CAU",

            //camera
            "https://i.pcmag.com/imagery/roundups/018cwxjHcVMwiaDIpTnZJ8H-23.1570842461.fit_lim.size_1200x630.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/9/99/LEI0440_Leica_IIIf_chrom_-_Sn._580566_1951-52-M39_Blitzsynchron_front_view-6531_hf-.jpg/1200px-LEI0440_Leica_IIIf_chrom_-_Sn._580566_1951-52-M39_Blitzsynchron_front_view-6531_hf-.jpg",
            "https://4.img-dpreview.com/files/p/E~TS520x0~articles/4497515678/best-mirrorless-m6ii.jpeg",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQb7NRdgoga1IQ1YviEP6LypSXRPDnL6hGKNA&usqp=CAU",
            "https://media.wired.com/photos/5d31f0327e21db0008efc4ee/master/pass/Gear-Sony-RX100VI-SOURCE-Sony.jpg",

            //keyboards
            "https://m.media-amazon.com/images/I/71nRfZNacyL._AC_SL1500_.jpg",
            "https://media.steelseriescdn.com/thumbs/catalogue/products/01101-apex-7-red-switch/2ff2d4b8587d4af3aeeae2e5fbb4a698.png.350x280_q100_crop-fit_optimize.png",
            "https://i.pcmag.com/imagery/reviews/01t7XnV3GQOMNj6k7uIunhp-1.1606229774.fit_lim.size_625x365.jpg",
            "https://cdn.vox-cdn.com/thumbor/G1xlcGD-NAc9xYn5s0uI9fD-DxE=/0x451:6300x3749/fit-in/1200x630/cdn.vox-cdn.com/uploads/chorus_asset/file/22219694/alloyorigins60.jpg",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRif8ZMLMo8G5KM74apW-xiyVH7y-ZKUtCTxA&usqp=CAU",
            //mouse
            "https://thermaltake.azureedge.net/pub/media/wysiwyg/key3/img/L20rgbmouse/aa.png",
            "https://5.imimg.com/data5/SELLER/Default/2020/11/UQ/ZH/FW/80385951/logitech-g-402-hyperion-fury-wired-gaming-mouse-500x500.jpg",
            "https://m.media-amazon.com/images/I/41pN96P0kSL._AC_SY780_.jpg",
            "https://m.media-amazon.com/images/I/51sL3ZYNqKS._AC_SY450_.jpg",
            "https://m.media-amazon.com/images/I/61SAa5qiaIL._AC_SL1000_.jpg",

            )

        listOfItems.clear()
        reverse(listOfImage)

        for ((i, x) in listOfImage.withIndex()) {
            listOfItems.add(
                ProductItem(
                    image1 = x,
                    price = "${(i + 1) * 200}",
                    title = when {
                        i < 5 -> {
                            "mouse $i"
                        }
                        i < 10 -> {
                            "keyboards $i"
                        }
                        i < 15 -> {
                            "cameras $i"
                        }
                        else -> {
                            "laptop $i"
                        }
                    },
                    category = when {
                        i < 5 -> {
                            "mouse"
                        }
                        i < 10 -> {
                            "keyboards"
                        }
                        i < 15 -> {
                            "cameras"
                        }
                        else -> {
                            "laptop"
                        }
                    },
                    categoryId = when {
                        i < 5 -> {
                            "4"
                        }
                        i < 10 -> {
                            "3"
                        }
                        i < 15 -> {
                            "2"
                        }
                        else -> {
                            "1"
                        }
                    },
                    rating = Random().nextInt(5),
                    barcode = "Bar Code $i",
                )
            )
        }

    }


    suspend fun getCategory(): Response<List<CategoryItem>> {
        delay(2000)
        return Response.success(listOfCategoryItem)

    }

    suspend fun getCategoryProductByID(productId: String): Response<List<ProductItem>> {
        delay(2000)

        return Response.success(listOfItems.filter { it.categoryId == productId })
    }

    suspend fun getCategoryProductByName(productName: String): Response<List<ProductItem>> {
        delay(2000)

        return Response.success(
            listOfItems.filter { it.category == productName }


        )
    }


    suspend fun getAllProducts(): Response<List<ProductItem>> {
        loadData()
        delay(2000)
        listOfItems.shuffle()
        return Response.success(
            listOfItems as List<ProductItem>
        )

    }

    fun filterProductByQueryName(query: String): Response<List<ProductItem>> {

        return Response.success(
            listOfItems.filter { it.title.contains(query) }
        )

    }


    fun checkOut(): Response<List<CartItem>> {
        cartItems.clear()
        return getProductInCart()
    }


    fun addItemToCart(item: CartItem): Response<List<CartItem>> {
        cartItems.add(item)
        Log.e("TAG", "addItemToCart: Add")
        return getProductInCart()
    }

    private val cartItems = mutableListOf<CartItem>()

    fun getProductInCart(): Response<List<CartItem>> {
        return Response.success(cartItems)
    }

    fun updateItem(count: Int, item: CartItem): Response<List<CartItem>> {
        if (count == 0) {
            cartItems.remove(item)
        } else {
            val index = cartItems.indexOf(item)
            item.count = item.count + count
            if (item.count < 0) {
                item.count = 0
            }
            cartItems[index] = item
        }

        return getProductInCart()
    }


}