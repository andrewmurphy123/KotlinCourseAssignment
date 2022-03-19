package com.example.kotlincourseassignment

import android.text.Layout
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Layout() {
    val nav = rememberNavController()

    Scaffold(
        topBar = { TopBar() },
        bottomBar = { BottomBar(nav) }
    ) {
        NavHost(navController = nav, startDestination = "home") {
            composable(route = "home") {
                MainView()
            }
            composable(route = "shopping_cart") {
                ShoppingCart(shoppingCartVM = ShoppingList())
            }

        }
    }
}

@Composable
fun TopBar() {
    val userVM = viewModel<UsersView>()

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(Color(0xFF52dcde))
    ) {
        Text(text = "Shopping Cart App",
             fontSize = 30.sp,
             textAlign = TextAlign.Left
        )
        Text(text = userVM.username.value)
        OutlinedButton(onClick = { userVM.logoutUser() }) {
            Text(text = "Log out")
        }
    }
}

@Composable
fun BottomBar(nav: NavHostController) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(Color(0xFF52dcde))

    ) {
        Icon(
            painterResource(id = R.drawable.ic_baseline_home_24),
                            contentDescription = "",
                            modifier = Modifier.clickable { nav.navigate("home") }
        )
        Icon(
            painterResource(id = R.drawable.ic_baseline_shopping_cart_24),
            contentDescription = "",
            modifier = Modifier.clickable { nav.navigate("shopping_cart") }
        )
    }
}