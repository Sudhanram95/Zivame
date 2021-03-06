package com.example.zivame_assignment.ui.cart.repository

import androidx.lifecycle.LiveData
import com.example.zivame_assignment.database.CartDao
import com.example.zivame_assignment.database.CartEntity
import com.example.zivame_assignment.database.ZivameDatabase
import javax.inject.Inject

class CartRepository @Inject constructor(val dbInstance: ZivameDatabase?) {

    var cartDao: CartDao? = null

    init {
        cartDao = dbInstance?.cartDao()
    }

    fun getAllItemsInCartTable(): LiveData<List<CartEntity>>? {
        return cartDao?.getAllItemsInCart()
    }

    fun removeItemFromCartTable(itemId: Int) {
        cartDao?.removeFromCart(itemId)
    }
}