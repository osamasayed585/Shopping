package com.hrhera.login.utils

import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import androidx.core.view.isVisible


object AnimationUtil {


    fun View.slideUp(animDuration: Int = 500) {
        visibility = View.INVISIBLE

        if (!this.isVisible) {
            val animate = TranslateAnimation(0f, 0f, this.height.toFloat(), 0f)
            animate.duration = animDuration.toLong()
            animate.fillAfter = true
            this.startAnimation(animate)
            animate.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(p0: Animation?) {

                }

                override fun onAnimationEnd(p0: Animation?) {
                    visibility = VISIBLE

                }

                override fun onAnimationRepeat(p0: Animation?) {
                }
            })
        }


    }


    fun View.slideDown(animDuration: Int = 500) {
        if (!this.isVisible) {
            val animate = TranslateAnimation(0f, 0f, -this.height.toFloat(), 0f)
            animate.duration = animDuration.toLong()
            animate.fillAfter = true
            this.startAnimation(animate)
            animate.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(p0: Animation?) {
                }

                override fun onAnimationEnd(p0: Animation?) {
                    visibility = VISIBLE
                }

                override fun onAnimationRepeat(p0: Animation?) {
                }
            })
        }
    }

    fun View.scaleUp(animDuration: Int = 500) {
        this.scaleX = 0f
        this.scaleY = 0f
        if (!this.isVisible) {
            this.visibility = VISIBLE
            this.animate().scaleX(1f).scaleY(1f).duration = animDuration.toLong()
        }
    }


    fun View.hid(animDuration: Int = 500) {
        if (this.isVisible) {
            val animate = TranslateAnimation(0f, 0f, 0f, this.height.toFloat())
            animate.duration = animDuration.toLong()
            animate.fillAfter = true
            this.startAnimation(animate)
            animate.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(p0: Animation?) {
                }

                override fun onAnimationEnd(p0: Animation?) {
                    visibility = INVISIBLE
                }

                override fun onAnimationRepeat(p0: Animation?) {
                }
            })
        }
    }


}