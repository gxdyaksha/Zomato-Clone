package com.example.zomato.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.zomato.data.local.UserPreferences
import com.example.zomato.ui.screens.auth.LoginScreen
import com.example.zomato.ui.screens.auth.RegisterScreen
import com.example.zomato.ui.screens.cart.CartScreen
import com.example.zomato.ui.screens.checkout.CheckoutScreen
import com.example.zomato.ui.screens.home.HomeScreen
import com.example.zomato.ui.screens.orders.OrdersScreen
import com.example.zomato.ui.screens.profile.ProfileScreen
import com.example.zomato.ui.screens.splash.SplashScreen
import com.example.zomato.viewmodel.CartViewModel

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val userPreferences = UserPreferences(context)
    val navController = rememberNavController()
    val cartViewModel: CartViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = NavRoutes.SPLASH,
        modifier = modifier
    ) {
        composable(NavRoutes.SPLASH) {
            SplashScreen(navController, userPreferences)
        }

        composable(NavRoutes.LOGIN) {
            LoginScreen(navController)
        }

        composable(NavRoutes.REGISTER) {
            RegisterScreen(navController)
        }

        composable(NavRoutes.HOME) {
            HomeScreen(navController, cartViewModel)
        }

        composable(NavRoutes.CART) {
            CartScreen(navController, cartViewModel)
        }

        composable(NavRoutes.CHECKOUT) {
            CheckoutScreen(navController, cartViewModel)
        }

        composable(NavRoutes.ORDERS) {
            OrdersScreen(cartViewModel)
        }

        composable(NavRoutes.PROFILE) {
            ProfileScreen(navController)
        }
    }
}