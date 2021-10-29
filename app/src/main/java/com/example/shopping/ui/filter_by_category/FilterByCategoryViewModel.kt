package com.example.shopping.ui.filter_by_category

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.shopping.model.data_class.CategoryItem
import com.example.shopping.model.data_class.ProductItem
import com.example.shopping.model.repository.ProductsRemoteRepositoryImp
import kotlinx.coroutines.launch

class FilterByCategoryViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = ProductsRemoteRepositoryImp()

    val categoryItemsMutableLiveData = MutableLiveData<List<ProductItem>>()
    val loaderMutableLiveData = MutableLiveData<Boolean>()

    fun updateDataByCategoryId(categoryId: String) {
        loaderMutableLiveData.value=true
        categoryItemsMutableLiveData.value= listOf()
        viewModelScope.launch {
            val value = repository.getAllCategoryProductsItemsByID(categoryId)
            loaderMutableLiveData.value=false
            categoryItemsMutableLiveData.value = value

        }
    }

    fun updateDataByCategoryName(categoryName: String) {
        loaderMutableLiveData.value=true
        categoryItemsMutableLiveData.value= listOf()
        viewModelScope.launch {

            val value = repository.getAllCategoryProductsItemsByName(categoryName)
            loaderMutableLiveData.value=false
            categoryItemsMutableLiveData.value = value
        }

    }


    val categoryMutableLiveData = MutableLiveData<List<CategoryItem>>()

    init {
        viewModelScope.launch {
            categoryMutableLiveData.postValue(repository.getAllCategory())
        }
    }


}