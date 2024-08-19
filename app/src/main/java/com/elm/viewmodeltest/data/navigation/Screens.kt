package com.elm.viewmodeltest.data.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.elm.viewmodeltest.view.screens.HomePage
import com.elm.viewmodeltest.view.screens.ProductDetailsPage
import com.elm.viewmodeltest.viewModel.LoginViewModdel
import com.elm.viewmodeltest.viewModel.ProductViewModel
import com.example.app.LoginPage

sealed class Screen(val route: String) {
   data object Login : Screen("login")
    data object Home : Screen("home")
    data object ProductDetails : Screen("product_details/{productId}") {
        fun moveToProduct(productId: String) = "product_details/$productId"
    }
}
@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val viewModel: ProductViewModel = viewModel()
    val loginViewModel: LoginViewModdel = viewModel()

    NavHost(navController = navController, startDestination = Screen.Login.route) {
        composable(Screen.Login.route) {
            LoginPage(navController = navController,viewModel = loginViewModel)
        }
        composable(Screen.Home.route) {
            HomePage(navController = navController, viewModel = viewModel)
        }
        composable(Screen.ProductDetails.route) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")
            productId?.let { ProductDetailsPage(productId = it, viewModel = viewModel) }
        }
    }
}