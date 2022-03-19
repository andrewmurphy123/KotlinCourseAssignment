package com.example.kotlincourseassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kotlincourseassignment.ui.theme.KotlinCourseAssignmentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotlinCourseAssignmentTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Layout()
                }
            }
        }
    }
}

@Composable
fun MainView() {
    val userVM = viewModel<UsersView>()

    if(userVM.username.value.isEmpty()){
        LoginView(userVM)
    }else {
        Greeting()
    }
}

@Composable
fun Greeting() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFF2e9aa6)),
        contentAlignment = Alignment.Center,

    ) {
        Text(text = "Welcome to the Shopping Cart App\n\n", fontSize = 30.sp)
    }


}

@Composable
fun ShoppingCart(shoppingCartVM: ShoppingList) {

    var itemText by remember { mutableStateOf("") }
    var itemQuantity by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFF2e9aa6))
        .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OutlinedTextField(
            value = itemText ,
            onValueChange = { itemText = it },
            label = { Text(text = "Item name") })

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = itemQuantity ,
            onValueChange = { itemQuantity = it },
            label = { Text(text = "Item quantity") })

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedButton(
            onClick = { shoppingCartVM.addItem( Item(itemText) ) }
        ) {
            Text(text = "Add to list")
        }

        Spacer(modifier = Modifier.height(10.dp))

        shoppingCartVM.items.value.forEach {
            Divider(thickness = 2.dp)
            Text(text = it.message)
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedButton(onClick = { shoppingCartVM.removeItem( Item(itemText) ) } ) {
                Text(text = "Remove")
            }
        }
        Divider(thickness = 2.dp)
    }
}


@Composable
fun LoginView(userVM: UsersView) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = email ,
            onValueChange = { email = it },
            label = { Text(text = "Email") })
        OutlinedTextField(
            value = password ,
            onValueChange = { password = it },
            label = { Text(text = "Password") },
            visualTransformation = PasswordVisualTransformation()
        )
        OutlinedButton(onClick = { userVM.loginUser(email,password) }) {
            Text(text = "Login")
        }
    }

}