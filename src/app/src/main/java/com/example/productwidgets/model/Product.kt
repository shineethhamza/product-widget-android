package com.example.productwidgets

data class Product(
    val id: Long? = null,
    val name: String,
    val price: Int,
    val offer: Int? = null,
    val category: String,
    val imageUrl: String
)