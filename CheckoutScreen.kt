package com.example.zomato.ui.screens.checkout

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.zomato.navigation.NavRoutes
import com.example.zomato.viewmodel.CartViewModel

@Composable
fun CheckoutScreen(
    navController: NavController,
    cartViewModel: CartViewModel
) {
    val context = LocalContext.current

    var address by remember { mutableStateOf("") }
    var paymentMethod by remember { mutableStateOf("Cash on Delivery") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Checkout",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = address,
            onValueChange = { address = it },
            label = { Text("Delivery Address") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Select Payment Method",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        PaymentOption(
            title = "Cash on Delivery",
            selected = paymentMethod == "Cash on Delivery",
            onSelect = { paymentMethod = "Cash on Delivery" }
        )

        PaymentOption(
            title = "UPI",
            selected = paymentMethod == "UPI",
            onSelect = { paymentMethod = "UPI" }
        )

        PaymentOption(
            title = "Card",
            selected = paymentMethod == "Card",
            onSelect = { paymentMethod = "Card" }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Total Amount: ₹${cartViewModel.getTotalPrice()}",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                if (address.isBlank()) {
                    Toast.makeText(context, "Please enter address", Toast.LENGTH_SHORT).show()
                } else if (cartViewModel.getCartCount() == 0) {
                    Toast.makeText(context, "Cart is empty", Toast.LENGTH_SHORT).show()
                } else {
                    cartViewModel.placeOrder(address, paymentMethod)
                    Toast.makeText(context, "Order placed successfully", Toast.LENGTH_SHORT).show()
                    navController.navigate(NavRoutes.ORDERS) {
                        popUpTo(NavRoutes.CHECKOUT) { inclusive = true }
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Confirm Order")
        }
    }
}

@Composable
fun PaymentOption(
    title: String,
    selected: Boolean,
    onSelect: () -> Unit
) {
    androidx.compose.foundation.layout.Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        RadioButton(
            selected = selected,
            onClick = onSelect
        )
        Text(
            text = title,
            modifier = Modifier.padding(top = 12.dp)
        )
    }
}