package com.example.shopping

import android.app.Application
import android.content.Context
import com.example.shopping.model.data_class.RunType

class MApplication: Application() {

    init {
        instance = this
    }

    companion object {
        private lateinit var instance: MApplication
        val  TYPE=RunType.TEST

        fun getAppContext(): Context {
            return instance.applicationContext
        }
    }
}