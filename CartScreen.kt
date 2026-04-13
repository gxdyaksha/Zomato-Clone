package com.example.zomato.ui.screens.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.zomato.navigation.NavRoutes
import com.example.zomato.viewmodel.CartViewModel

@Composable
fun CartScreen(
    navController: NavController,
    cartViewModel: CartViewModel
) {
    val cartEntries = cartViewModel.cartItems.entries.toList()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "My Cart",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(12.dp))

        if (cartEntries.isEmpty()) {
            Text("Your cart is empty")
        } else {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(cartEntries) { entry ->
                    val item = entry.key
                    val qty = entry.value

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(item.name, style = MaterialTheme.typography.titleMedium)
                            Text(item.restaurant)
                            Text("₹${item.price}")
                            Text("Qty: $qty")
                            Text("Subtotal: ₹${item.price * qty}")

                            Spacer(modifier = Modifier.height(8.dp))

                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                Button(onClick = { cartViewModel.decreaseQty(item) }) {
                                    Text("-")
                                }
                                Button(onClick = { cartViewModel.increaseQty(item) }) {
                                    Text("+")
                                }
                                Button(onClick = { cartViewModel.removeFromCart(item) }) {
                                    Text("Remove")
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text("Items: ${cartViewModel.getCartCount()}")
            Text(
                text = "Total: ₹${cartViewModel.getTotalPrice()}",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    navController.navigate(NavRoutes.CHECKOUT)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Proceed to Checkout")
            }
        }
    }
}