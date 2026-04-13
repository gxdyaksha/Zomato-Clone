package com.example.zomato.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.zomato.data.local.FakeFoodRepository
import com.example.zomato.data.model.FoodItem
import com.example.zomato.data.model.OrderItem
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CartViewModel : ViewModel() {

    private val allFoods = FakeFoodRepository.getFoods()

    var searchText by mutableStateOf("")
        private set

    var selectedCategory by mutableStateOf("All")
        private set

    private val _cartItems = mutableStateMapOf<FoodItem, Int>()
    val cartItems: Map<FoodItem, Int> get() = _cartItems

    private val _orders = mutableStateListOf<OrderItem>()
    val orders: List<OrderItem> = _orders

    fun updateSearch(text: String) {
        searchText = text
    }

    fun selectCategory(category: String) {
        selectedCategory = category
    }

    fun getFoods(): List<FoodItem> {
        return allFoods.filter { food ->
            val matchesSearch =
                food.name.contains(searchText, ignoreCase = true) ||
                        food.restaurant.contains(searchText, ignoreCase = true) ||
                        food.category.contains(searchText, ignoreCase = true)

            val matchesCategory =
                selectedCategory == "All" || food.category == selectedCategory

            matchesSearch && matchesCategory
        }
    }

    fun addToCart(item: FoodItem) {
        val currentQty = _cartItems[item] ?: 0
        _cartItems[item] = currentQty + 1
    }

    fun increaseQty(item: FoodItem) {
        val currentQty = _cartItems[item] ?: 0
        _cartItems[item] = currentQty + 1
    }

    fun decreaseQty(item: FoodItem) {
        val currentQty = _cartItems[item] ?: 0
        if (currentQty <= 1) {
            _cartItems.remove(item)
        } else {
            _cartItems[item] = currentQty - 1
        }
    }

    fun removeFromCart(item: FoodItem) {
        _cartItems.remove(item)
    }

    fun getItemQty(item: FoodItem): Int {
        return _cartItems[item] ?: 0
    }

    fun getCartCount(): Int {
        return _cartItems.values.sum()
    }

    fun getTotalPrice(): Int {
        return _cartItems.entries.sumOf { (food, qty) -> food.price * qty }
    }

    fun placeOrder(address: String, paymentMethod: String) {
        if (_cartItems.isNotEmpty()) {
            val expandedItems = buildList {
                _cartItems.forEach { (food, qty) ->
                    repeat(qty) { add(food) }
                }
            }

            val time = SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault()).format(Date())

            val order = OrderItem(
                id = _orders.size + 1,
                items = expandedItems,
                totalPrice = getTotalPrice(),
                totalItems = getCartCount(),
                address = address,
                paymentMethod = paymentMethod,
                orderTime = time
            )

            _orders.add(0, order)
            _cartItems.clear()
        }
    }

    fun deleteOrder(orderItem: OrderItem) {
        _orders.remove(orderItem)
    }

    fun clearAllOrders() {
        _orders.clear()
    }

    fun clearSearchAndFilter() {
        searchText = ""
        selectedCategory = "All"
    }
}