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

class ProductBrandsViewPagerAdapter(
    var context: Context,
    var ProductBrandsList: List<ProductItem>
) : PagerAdapter() {

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return ProductBrandsList.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val view = LayoutInflater.from(context)
            .inflate(R.layout.item_pager_ptodduct_brands, container, false)

        val image = view.findViewById<ImageView>(R.id.brands_image)
        val title = view.findViewById<TextView>(R.id.brands_title)


        Picasso.get().load(ProductBrandsList[position].image1).centerCrop().fit().into(image)
        title.text = ProductBrandsList[position].title

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