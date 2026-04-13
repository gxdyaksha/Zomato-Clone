package com.example.zomato.data.local

import com.example.zomato.R
import com.example.zomato.data.model.FoodItem

object FakeFoodRepository {

    fun getFoods(): List<FoodItem> {
        return listOf(
            FoodItem(
                id = 1,
                name = "Veg Biryani",
                restaurant = "Biryani House",
                price = 180,
                rating = 4.3,
                category = "Biryani",
                description = "Aromatic veg biryani with rich spices",
                image = R.drawable.veg_biryani
            ),
            FoodItem(
                id = 2,
                name = "Chicken Biryani",
                restaurant = "Biryani House",
                price = 240,
                rating = 4.6,
                category = "Biryani",
                description = "Spicy chicken dum biryani",
                image = R.drawable.chicken_biryani
            ),
            FoodItem(
                id = 3,
                name = "Burger",
                restaurant = "Burger Point",
                price = 120,
                rating = 4.1,
                category = "Burger",
                description = "Loaded burger with sauces",
                image = R.drawable.burger
            ),
            FoodItem(
                id = 4,
                name = "Cheese Burger",
                restaurant = "Burger Point",
                price = 160,
                rating = 4.4,
                category = "Burger",
                description = "Grilled burger with extra cheese",
                image = R.drawable.cheese_burger
            ),
            FoodItem(
                id = 5,
                name = "Pizza",
                restaurant = "Pizza Hub",
                price = 250,
                rating = 4.5,
                category = "Pizza",
                description = "Classic cheesy pizza with toppings",
                image = R.drawable.pizza
            ),
            FoodItem(
                id = 6,
                name = "Farmhouse Pizza",
                restaurant = "Pizza Hub",
                price = 320,
                rating = 4.7,
                category = "Pizza",
                description = "Loaded farmhouse pizza with veggies",
                image = R.drawable.farmhouse_pizza
            ),
            FoodItem(
                id = 7,
                name = "Paneer Roll",
                restaurant = "Roll Center",
                price = 90,
                rating = 4.0,
                category = "Rolls",
                description = "Soft paneer roll with tasty filling",
                image = R.drawable.paneer_roll
            ),
            FoodItem(
                id = 8,
                name = "Chinese Combo",
                restaurant = "Wok Express",
                price = 210,
                rating = 4.2,
                category = "Chinese",
                description = "Noodles and manchurian combo",
                image = R.drawable.chinese_combo
            ),
            FoodItem(
                id = 9,
                name = "Momos",
                restaurant = "Wok Express",
                price = 140,
                rating = 4.1,
                category = "Chinese",
                description = "Steamed momos with spicy chutney",
                image = R.drawable.momos
            ),
            FoodItem(
                id = 10,
                name = "Cold Coffee",
                restaurant = "Cafe Street",
                price = 110,
                rating = 4.3,
                category = "Drinks",
                description = "Creamy cold coffee",
                image = R.drawable.coffee
            )
        )
    }

    fun getCategories(): List<String> {
        return listOf(
            "All",
            "Pizza",
            "Burger",
            "Biryani",
            "Rolls",
            "Chinese",
            "Drinks"
        )
    }
}