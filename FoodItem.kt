package com.example.zomato.data.model

data class FoodItem(
    val id: Int,
    val name: String,
    val restaurant: String,
    val price: Int,
    val rating: Double,
    val category: String,
    val description: String = "",
    val image: Int
)