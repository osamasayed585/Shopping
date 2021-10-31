package com.example.shopping.model.local


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shopping.model.data_class.ProductItem


@Database(entities = [ProductItem::class], version = 1)
abstract class DataBaseHelper : RoomDatabase() {

    abstract fun productsDao(): ProductsDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: DataBaseHelper? = null

        fun getInstance(context: Context): DataBaseHelper {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DataBaseHelper::class.java,
                    "ShoppingAppDataBase"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }


}
