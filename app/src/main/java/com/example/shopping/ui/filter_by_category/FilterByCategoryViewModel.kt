package com.example.shopping.ui.filter_by_category

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.shopping.model.data_class.CategoryItem
import com.example.shopping.model.data_class.ProductItem
import com.example.shopping.model.remote.ShopRemoteBuilder
import com.example.shopping.model.remote.ShoppingAPI
import com.example.shopping.model.repository.ProductsRemoteRepositoryImp
import kotlinx.coroutines.launch

class FilterByCategoryViewModel(application: Application) : AndroidViewModel(application) {

    private val service = ShopRemoteBuilder.productBuilder().create(ShoppingAPI::class.java)
    private val repository = ProductsRemoteRepositoryImp(service)

    val categoryItemsMutableLiveData = MutableLiveData<List<ProductItem>>()
    val loaderMutableLiveData = MutableLiveData<Boolean>()

    fun updateDataByCategoryId(categoryId: String) {
        loaderMutableLiveData.value=true
        categoryItemsMutableLiveData.value= listOf()
        viewModelScope.launch {
            val value = repository.getAllCategoryProductsItemsByID(categoryId)
            loaderMutableLiveData.value=false
            categoryItemsMutableLiveData.value = value.body()

        }
    }

    fun updateDataByCategoryName(categoryName: String) {
        loaderMutableLiveData.value=true
        categoryItemsMutableLiveData.value= listOf()
        viewModelScope.launch {

            val value = repository.getAllCategoryProductsItemsByName(categoryName)
            loaderMutableLiveData.value=false
            categoryItemsMutableLiveData.value = value.body()
        }

    }


    val categoryMutableLiveData = MutableLiveData<List<CategoryItem>>()

    init {
        viewModelScope.launch {
            categoryMutableLiveData.postValue(repository.getAllCategory().body())
        }
    }


}