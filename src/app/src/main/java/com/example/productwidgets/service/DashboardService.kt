package com.example.productwidgets.service

import com.example.productwidgets.Product
import com.example.productwidgets.helpers.JsonReader
import com.example.productwidgets.model.Banner
import com.google.gson.Gson
import java.lang.Error
import java.lang.Exception

object DashboardService {
    fun getOfferedCategory(completionHandler: ((List<Product>?, Error?) -> Unit)) {

        var response: List<Product>? = null
        var error: Error? = null
        //TODO: integrate actual API here
        try {
            val result = JsonReader.readFromFile("ProductList.json")
            when {
                result != null -> {
                    val list: MutableList<Product> =
                        Gson().fromJson(result, Array<Product>::class.java).toMutableList()
                    list.sortByDescending {
                        it.offer
                    }
                    response = list.distinctBy {
                        it.category
                    }
                }
                else ->
                    error = Error("Something Went Wrong")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            error = Error(e.localizedMessage)
        }
        completionHandler(response, error)
    }


    fun getCarouselList(completionHandler: ((List<Banner>?, Error?) -> Unit)) {
        var response: List<Banner>? = null
        var error: Error? = null

        //TODO: integrate actual API here
        try {
            val result = JsonReader.readFromFile("CarouselList.json")
            when {
                result != null ->
                    response = Gson().fromJson(result, Array<Banner>::class.java).toList()


                else ->
                    error = Error("Something Went Wrong")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            error = Error(e.localizedMessage)
        }
        completionHandler(response, error)
    }

    fun getBannerList(completionHandler: ((List<Banner>?, Error?) -> Unit)) {
        var response: List<Banner>? = null
        var error: Error? = null

        //TODO: integrate actual API here
        try {
            val result = JsonReader.readFromFile("BannerList.json")
            when {
                result != null ->
                    response = Gson().fromJson(result, Array<Banner>::class.java).toList()


                else ->
                    error = Error("Something Went Wrong")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            error = Error(e.localizedMessage)
        }
        completionHandler(response, error)
    }
}