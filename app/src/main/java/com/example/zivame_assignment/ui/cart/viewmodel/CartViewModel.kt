package com.example.zivame_assignment.ui.cart.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zivame_assignment.database.CartEntity
import com.example.zivame_assignment.ui.cart.repository.CartRepository
import javax.inject.Inject

class CartViewModel @Inject constructor(val repository: CartRepository) : ViewModel() {

    private val cartListLiveData = MutableLiveData<List<CartEntity>>()
    private val resultLiveData = MutableLiveData<String>()

    fun getCartList() = cartListLiveData
    fun getResult() = resultLiveData

    fun fetchAllItemsInCart() {
        cartListLiveData.value = repository.getAllItemsInCartTable()
    }

    fun removeItemFromCart(itemId: Int) {
        repository.removeItemFromCartTable(itemId)
        resultLiveData.value = "Gadget removed from cart"
    }
}