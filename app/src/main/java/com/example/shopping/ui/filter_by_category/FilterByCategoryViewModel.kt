package com.example.shopping.ui.filter_by_category

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.shopping.model.data_class.CategoryItem
import com.example.shopping.model.data_class.ProductItem
import com.example.shopping.model.remote.ShopRemoteBuilder
import com.example.shopping.model.remote.ShoppingAPI
import com.example.shopping.model.repository.ProductsRemoteRepositoryImp
import com.hrhera.login.model.remote.RemoteRepositoryImp
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FilterByCategoryViewModel(application: Application) : AndroidViewModel(application) {
    private val service = ShopRemoteBuilder.productBuilder().create(ShoppingAPI::class.java)
    private val repository = ProductsRemoteRepositoryImp(service)

    val categoryItemsMutableLiveData = MutableLiveData<List<ProductItem>>()

    fun updateDataByCategoryId(categoryId: String) {

    }

    fun updateDataByCategoryName(categoryName: String) {

    }


    val categoryMutableLiveData = MutableLiveData<List<CategoryItem>>()

    init {
        GlobalScope.launch {
            categoryMutableLiveData.postValue(repository.getAllCategory().body())
        }
    }


}