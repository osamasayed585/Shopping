package com.example.shopping.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.viewModelScope
import androidx.viewpager.widget.PagerAdapter
import com.example.shopping.R
import com.example.shopping.model.data_class.CartItem

import com.example.shopping.model.data_class.ProductItem
import com.example.shopping.util.call_back.OnRecyclerItemClick
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch

class HotProductViewPagerAdapter(var context: Context, var ProductList: List<ProductItem>): PagerAdapter() {

    companion object{
        lateinit var addToCartClick: OnRecyclerItemClick
        lateinit var addToFavouriteClick: OnRecyclerItemClick
        lateinit var removeFromFavouriteClick: OnRecyclerItemClick
    }


    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return ProductList.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val view = LayoutInflater.from(context).inflate(R.layout.row_hotproduct_item, container, false)

        val image = view.findViewById<ImageView>(R.id.hotProduct_image)
        val title = view.findViewById<TextView>(R.id.hotProduct_title)
        val barcode = view.findViewById<TextView>(R.id.hotProduct_barcode)
        val rating = view.findViewById<RatingBar>(R.id.hotProduct_rating)
        val favourite = view.findViewById<ImageButton>(R.id.hotProduct_favourite)
        val cart = view.findViewById<ImageButton>(R.id.hotProduct_cart)


        Picasso.get().load(ProductList[position].image1).centerCrop().fit().into(image)
        title.text = ProductList[position].title
        barcode.text = ProductList[position].barcode
        rating.rating = ProductList[position].rating.toFloat()

        favourite.setOnClickListener {
            Toast.makeText(context, "added", Toast.LENGTH_SHORT).show()
        }
        cart.setOnClickListener{
            addToCartClick.onItemClick(ProductList[position])
        }

        view.setOnClickListener {
            Toast.makeText(context, "Loading...", Toast.LENGTH_SHORT).show()
        }
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }

}