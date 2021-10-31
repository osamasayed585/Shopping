package com.example.shopping.model.local


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.shopping.model.data_class.ProductItem

@Dao
interface ProductsDao {

    @Insert     // insert message to database
    fun insert(productItem: ProductItem)

    @Query("Delete from Products Where id=:id ")
    fun delete(id:String )

    @get:Query("Select * from Products ")
    val allFavouriteProducts: LiveData<List<ProductItem>>

    @Query("DELETE FROM Products")
    fun clearTable()

}
