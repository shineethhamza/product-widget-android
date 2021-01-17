package com.example.productwidgets.service

import com.example.productwidgets.Product
import com.example.productwidgets.helpers.JsonReader
import com.google.gson.Gson
import java.lang.Error
import java.lang.Exception

object ProductService {
    fun getProducts(completionHandler: ((List<Product>?, Error?) -> Unit)) {

        var response: List<Product>? = null
        var error: Error? = null

        //TODO: integrate actual API here
        try {
            val result = JsonReader.readFromFile("ProductList.json")
            when {
                result != null -> {
                    response =
                        Gson().fromJson(result, Array<Product>::class.java).toList()
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
}