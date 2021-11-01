package com.example.shopping.util

import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentActivity
import com.example.shopping.R


fun Fragment.initToolbar(toolBar: Toolbar, titleResId: Int, backEnable: Boolean) {
    val appCompatActivity = activity as AppCompatActivity
    appCompatActivity.setSupportActionBar(toolBar)
    appCompatActivity.supportActionBar?.setTitle(titleResId)
    appCompatActivity.supportActionBar?.setDisplayHomeAsUpEnabled(backEnable)
}

 fun FragmentActivity.getScreenWidth(): Int {


    val display = this.windowManager.defaultDisplay

    val density = resources.displayMetrics.density

    val size = Point();
    display.getSize(size);
    val width = size.x;
    Log.e("Width", "" + width);
    return width
}