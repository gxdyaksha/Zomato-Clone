package com.example.zomato.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.zomato.data.local.FakeFoodRepository
import com.example.zomato.data.model.BottomNavItem
import com.example.zomato.navigation.NavRoutes
import com.example.zomato.ui.component.BottomBar
import com.example.zomato.ui.component.FoodCard
import com.example.zomato.ui.theme.BackgroundGray
import com.example.zomato.ui.theme.TextPrimary
import com.example.zomato.ui.theme.TextSecondary
import com.example.zomato.ui.theme.ZomatoRed
import com.example.zomato.viewmodel.CartViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    cartViewModel: CartViewModel
) {
    val categories = FakeFoodRepository.getCategories()
    val foodList = cartViewModel.getFoods()

    val bottomItems = listOf(
        BottomNavItem(NavRoutes.HOME, "Home"),
        BottomNavItem(NavRoutes.CART, "Cart"),
        BottomNavItem(NavRoutes.ORDERS, "Orders"),
        BottomNavItem(NavRoutes.PROFILE, "Profile")
    )

    Scaffold(
        containerColor = BackgroundGray,
        bottomBar = {
            BottomBar(
                navController = navController,
                items = bottomItems,
                cartCount = cartViewModel.getCartCount()
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundGray)
                .padding(padding)
        ) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = ZomatoRed,
                            shape = RoundedCornerShape(bottomStart = 28.dp, bottomEnd = 28.dp)
                        )
                        .statusBarsPadding()
                        .padding(18.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.LocationOn,
                                    contentDescription = "Location",
                                    tint = Color.White
                                )
                                Spacer(modifier = Modifier.padding(2.dp))
                                Text(
                                    text = "Home",
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            Spacer(modifier = Modifier.height(6.dp))

                            Text(
                                text = "Delivering to Your Location",
                                color = Color.White.copy(alpha = 0.9f)
                            )
                        }

                        TextButton(
                            onClick = { navController.navigate(NavRoutes.CART) },
                            colors = ButtonDefaults.textButtonColors(contentColor = Color.White)
                        ) {
                            Icon(
                                imageVector = Icons.Default.ShoppingCart,
                                contentDescription = "Cart"
                            )
                            Spacer(modifier = Modifier.padding(2.dp))
                            Text("(${cartViewModel.getCartCount()})")
                        }
                    }

                    Spacer(modifier = Modifier.height(18.dp))

                    OutlinedTextField(
                        value = cartViewModel.searchText,
                        onValueChange = { cartViewModel.updateSearch(it) },
                        placeholder = {
                            Text("Search food, restaurant...", color = TextSecondary)
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search"
                            )
                        },
                        shape = RoundedCornerShape(18.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .background(
                            color = Color(0xFFFFE8EA),
                            shape = RoundedCornerShape(22.dp)
                        )
                        .padding(18.dp)
                ) {
                    Column {
                        Text(
                            text = "Up to 50% OFF",
                            color = ZomatoRed,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Enjoy delicious food from top restaurants near you.",
                            color = TextPrimary
                        )
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))
            }

            item {
                Text(
                    text = "Categories",
                    modifier = Modifier.padding(horizontal = 16.dp),
                    color = TextPrimary,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(10.dp))

                LazyRow(
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    items(categories) { category ->
                        val isSelected = cartViewModel.selectedCategory == category

                        Box(
                            modifier = Modifier
                                .padding(end = 10.dp)
                                .background(
                                    color = if (isSelected) ZomatoRed else Color.White,
                                    shape = RoundedCornerShape(18.dp)
                                )
                                .padding(horizontal = 18.dp, vertical = 10.dp)
                        ) {
                            Text(
                                text = category,
                                color = if (isSelected) Color.White else TextPrimary,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                modifier = Modifier
                                    .align(Alignment.Center)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Popular Food",
                        color = TextPrimary,
                        fontWeight = FontWeight.Bold
                    )

                    Button(
                        onClick = { cartViewModel.clearSearchAndFilter() },
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                    ) {
                        Text("Reset", color = ZomatoRed)
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
            }

            items(foodList) { item ->
                FoodCard(
                    item = item,
                    qty = cartViewModel.getItemQty(item),
                    onAdd = { cartViewModel.addToCart(item) },
                    onIncrease = { cartViewModel.increaseQty(item) },
                    onDecrease = { cartViewModel.decreaseQty(item) }
                )
            }

            item {
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}