package com.example.productwidgets.view.product_list.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.productwidgets.Product
import com.example.productwidgets.service.DashboardService
import com.example.productwidgets.service.ProductService

class ProductListViewModel : ViewModel() {
    var products = MutableLiveData<List<Product>>().apply {

        ProductService.getProducts { list, error ->
            when{
                list != null -> postValue(list)
                error != null -> Log.e("Error on product list", error.localizedMessage)
            }
        }
    }
}