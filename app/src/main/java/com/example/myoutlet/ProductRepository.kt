package com.example.myoutlet

import com.example.myoutlet.services.ProductsServices

class ProductRepository(private val productsServices: ProductsServices) {

  fun getAllProduct() = productsServices

}