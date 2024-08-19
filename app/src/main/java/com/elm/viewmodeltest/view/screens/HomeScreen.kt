package com.elm.viewmodeltest.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.elm.viewmodeltest.data.navigation.Screen
import com.elm.viewmodeltest.data.Product
import com.elm.viewmodeltest.viewModel.ProductViewModel
@Composable
fun HomePage(navController: NavController, viewModel: ProductViewModel) {
    val products by viewModel.products.collectAsState()

    Column(modifier = Modifier.padding(16.dp).statusBarsPadding()) {
        Button(onClick = { viewModel.loadProducts() }) {
            Text("Load Data")
        }
        if (products.isNotEmpty()) {
            LazyColumn {
                items(products) { product ->
                    ProductListItem(product = product, onClick = {
                        navController.navigate(Screen.ProductDetails.moveToProduct(product.id))
                    })
                }
            }
        }
    }
}

@Composable
fun ProductListItem(product: Product, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = product.imageUrl),
            contentDescription = null,
            modifier = Modifier.size(64.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(product.title, style = MaterialTheme.typography.titleMedium)
    }
}
