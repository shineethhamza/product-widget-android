package com.example.productwidgets.view.dashboard

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.productwidgets.Product
import com.example.productwidgets.model.Banner
import com.example.productwidgets.service.DashboardService
import org.imaginativeworld.whynotimagecarousel.CarouselItem

class DashboardViewModel : ViewModel() {
    var carouselList = MutableLiveData<MutableList<CarouselItem>>().apply {
        DashboardService.getCarouselList { list, error ->
            when{
                list != null -> {
                    val items: MutableList<CarouselItem> = mutableListOf()
                    list.forEach {
                        items.add(CarouselItem(imageUrl = it.imageUrl))
                    }
                    postValue(items)
                }
                error != null -> Log.e("Error on product list", error.localizedMessage)
            }
        }
    }

    var bannerList = MutableLiveData<List<Banner>>().apply {
        DashboardService.getBannerList { list, error ->
            when{
                list != null -> postValue(list)
                error != null -> Log.e("Error on product list", error.localizedMessage)
            }
        }
    }

    var offerProducts = MutableLiveData<List<Product>>().apply {

        DashboardService.getOfferedCategory { list, error ->
            when{
                list != null -> postValue(list)
                error != null -> Log.e("Error on product list", error.localizedMessage)
            }
        }
    }
}