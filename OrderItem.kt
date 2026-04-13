package com.example.zomato.data.model

data class OrderItem(
    val id: Int,
    val items: List<FoodItem>,
    val totalPrice: Int,
    val totalItems: Int,
    val address: String,
    val paymentMethod: String,
    val orderTime: String
)