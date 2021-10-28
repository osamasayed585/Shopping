package com.example.shopping.ui.filter_by_category

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.shopping.model.data_class.CategoryItem
import com.example.shopping.model.data_class.ProductItem
import com.example.shopping.model.repository.DataRepository
import kotlinx.coroutines.launch

class FilterByCategoryViewModel(application: Application) : AndroidViewModel(application) {

    val categoryItemsMutableLiveData = MutableLiveData<List<ProductItem>>()
    val loaderMutableLiveData = MutableLiveData<Boolean>()
    val categoryItemMutableLiveData = MutableLiveData<CategoryItem>()

    private val repository= DataRepository()

    private fun updateDataByCategoryId(categoryId: String) {
        loaderMutableLiveData.value = true
        categoryItemsMutableLiveData.value = listOf()
        viewModelScope.launch {
            val value = repository.getAllCategoryProductsItemsByID(categoryId)
            loaderMutableLiveData.value = false
            categoryItemsMutableLiveData.value = value
        }
    }

    private fun updateDataByCategoryName(categoryName: String) {
        loaderMutableLiveData.value = true
        categoryItemsMutableLiveData.value = listOf()
        viewModelScope.launch {

            val value = repository.getAllCategoryProductsItemsByName(categoryName)
            loaderMutableLiveData.value = false
            categoryItemsMutableLiveData.value = value
        }

    }


    val categoryMutableLiveData = MutableLiveData<List<CategoryItem>>()

    init {
        viewModelScope.launch {
            categoryMutableLiveData.postValue(repository.getAllCategory())
        }
    }

    fun setCategory(categoryItem: CategoryItem) {
        categoryItemMutableLiveData.value = categoryItem
        updateDataByCategoryName(categoryItem.title)
        updateDataByCategoryId(categoryItem.id)

    }


}