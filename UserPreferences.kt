package com.example.zomato.data.local

import android.content.Context

class UserPreferences(context: Context) {

    private val prefs = context.getSharedPreferences("zomato_prefs", Context.MODE_PRIVATE)

    fun saveUser(name: String, email: String, password: String) {
        prefs.edit()
            .putString("name", name)
            .putString("email", email)
            .putString("password", password)
            .apply()
    }

    fun loginUser(email: String, password: String): Boolean {
        val savedEmail = prefs.getString("email", "")
        val savedPassword = prefs.getString("password", "")
        return email == savedEmail && password == savedPassword
    }

    fun setLoggedIn(isLoggedIn: Boolean) {
        prefs.edit().putBoolean("is_logged_in", isLoggedIn).apply()
    }

    fun isLoggedIn(): Boolean {
        return prefs.getBoolean("is_logged_in", false)
    }

    fun getUserName(): String {
        return prefs.getString("name", "User") ?: "User"
    }

    fun getUserEmail(): String {
        return prefs.getString("email", "") ?: ""
    }

    fun logout() {
        prefs.edit().putBoolean("is_logged_in", false).apply()
    }
}