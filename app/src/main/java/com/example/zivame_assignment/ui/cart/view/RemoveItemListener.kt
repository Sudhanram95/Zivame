package com.example.zivame_assignment.ui.cart.view

import com.example.zivame_assignment.database.CartEntity

interface RemoveItemListener {
    fun onRemoveItemFromCart(position: Int, cartEntity: CartEntity)
}