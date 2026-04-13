package com.example.zomato.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ListAlt
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.zomato.data.model.BottomNavItem
import com.example.zomato.navigation.NavRoutes

@Composable
fun BottomBar(
    navController: NavController,
    items: List<BottomNavItem>,
    cartCount: Int
) {
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    NavigationBar {
        items.forEach { item ->
            val icon = when (item.route) {
                NavRoutes.HOME -> Icons.Default.Home
                NavRoutes.CART -> Icons.Default.ShoppingCart
                NavRoutes.ORDERS -> Icons.Default.ListAlt
                else -> Icons.Default.Person
            }

            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        launchSingleTop = true
                    }
                },
                icon = {
                    if (item.route == NavRoutes.CART && cartCount > 0) {
                        BadgedBox(
                            badge = { Badge { Text(cartCount.toString()) } }
                        ) {
                            Icon(icon, contentDescription = item.title)
                        }
                    } else {
                        Icon(icon, contentDescription = item.title)
                    }
                },
                label = { Text(item.title) }
            )
        }
    }
}