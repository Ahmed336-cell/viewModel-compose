package com.elm.viewmodeltest.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elm.viewmodeltest.R
import com.elm.viewmodeltest.data.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductViewModel:ViewModel() {
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> get() = _products

    fun loadProducts() {
        viewModelScope.launch {
            _products.value = listOf(
                Product(
                    id = "1",
                    title = "Orange",
                    imageUrl = R.drawable.orange,
                    description = "A juicy orange",
                    price = 3.99,
                    quantity = 4
                ),
                Product(
                    id = "2",
                    imageUrl = R.drawable.grapps,
                    title = "Grapes",
                    price = 5.99,
                    quantity = 1,
                    description = "A bunch of sweet grapes"
                ),
                Product(
                    id = "3",
                    imageUrl = R.drawable.strawberry,
                    title = "Strawberry",
                    price = 6.99,
                    quantity = 12,
                    description = "A pack of fresh strawberries"
                ),
                Product(
                    id = "4",
                    imageUrl = R.drawable.watermelon,
                    title = "Watermelon",
                    price = 7.99,
                    quantity = 1,
                    description = "A large watermelon"
                ),
                Product(
                    id = "5",
                    imageUrl = R.drawable.mango,
                    title = "Mango",
                    price = 3.49,
                    quantity = 3,
                    description = "A ripe mango"
                ),
                Product(
                    id = "6",
                    imageUrl = R.drawable.pineapple,
                    title = "Pineapple",
                    price = 5.49,
                    quantity = 1,
                    description = "A fresh pineapple"
                )
            )
        }
    }


    fun getProductById(productId: String): Product? {
        return _products.value.find { it.id == productId }
    }

}