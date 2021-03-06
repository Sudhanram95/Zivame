package com.example.zivame_assignment.ui.gadgetlist.view

import com.example.zivame_assignment.ui.gadgetlist.model.ProductModel

interface AddToCartListener {
    fun onAddToCart(id: Int, productModel: ProductModel)
}