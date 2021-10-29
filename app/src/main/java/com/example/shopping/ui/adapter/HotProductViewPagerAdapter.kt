package com.example.shopping.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.viewpager.widget.PagerAdapter
import com.example.shopping.R

import com.example.shopping.model.data_class.ProductItem
import com.squareup.picasso.Picasso

class HotProductViewPagerAdapter(var context: Context, var ProductList: List<ProductItem>): PagerAdapter() {

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return ProductList.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val view = LayoutInflater.from(context).inflate(R.layout.hotproduct_item, container, false)

        val image = view.findViewById<ImageView>(R.id.hot_image)
        val title = view.findViewById<TextView>(R.id.hotItem_title)
        val barcode = view.findViewById<TextView>(R.id.hotItem_barcode)
        val rating = view.findViewById<RatingBar>(R.id.hotItem_rating)
        val favourite = view.findViewById<ImageButton>(R.id.hotItem_favourite)
        val cart = view.findViewById<ImageButton>(R.id.hotItem_cart)


        Picasso.get().load(ProductList[position].image1).centerCrop().fit().into(image)
        title.text = ProductList[position].title
        barcode.text = ProductList[position].barcode
        rating.rating = ProductList[position].rating.toFloat()

        favourite.setOnClickListener {
            Toast.makeText(context, "added", Toast.LENGTH_SHORT).show()
        }
        cart.setOnClickListener{
            Toast.makeText(context, "added", Toast.LENGTH_SHORT).show()
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