package com.elm.viewmodeltest.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elm.viewmodeltest.viewModel.ProductViewModel

@Composable
fun ProductDetailsPage(productId: String,viewModel: ProductViewModel ) {

    val product = viewModel.getProductById(productId)
    if (product != null) {
        Column(modifier = Modifier.padding(16.dp).statusBarsPadding()) {
            Image(
                painter = painterResource( product.imageUrl),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(product.title, style = MaterialTheme.typography.titleLarge , fontSize = 22.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Price: \$${product.price}", style = MaterialTheme.typography.bodyLarge , fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Quantity: ${product.quantity}", style = MaterialTheme.typography.bodyMedium, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(product.description, style = MaterialTheme.typography.bodyLarge, fontSize = 16.sp)
        }
    } else {
        Text("Product not found", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(16.dp))
    }
}