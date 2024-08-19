package com.example.app

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.elm.viewmodeltest.data.navigation.Screen
import com.elm.viewmodeltest.viewModel.LoginState
import com.elm.viewmodeltest.viewModel.LoginViewModdel

@Composable
fun LoginPage(navController: NavController, viewModel: LoginViewModdel) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var error by remember {
        mutableStateOf("")
    }
    val loginState by viewModel.loginState.collectAsState()


    LaunchedEffect(loginState) {
        when (loginState) {
            is LoginState.Success -> {
                viewModel.resetState()
                navController.navigate(Screen.Home.route)
            }
            is LoginState.Failed -> {
                if (username.length < 4 || password.length < 4)
                    error = "Username and Password must be at least 4 characters"
                else
                error="Wrong User name or Password"
                viewModel.resetState()
            }
            else -> {}
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username") }
            )
            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation()
            )
            Button(onClick ={
                if (username.isEmpty() || password.isEmpty()) {
                    error = "Please fill all fields"
                    return@Button
                }
                viewModel.login(username, password)

            }) {
                Text("Login")
            }
            when (loginState) {
                is LoginState.Loading -> {
                    CircularProgressIndicator()
                }

                else -> {
                    if(error.isNotEmpty())
                    Text(error)

                }
            }
        }
    }
}