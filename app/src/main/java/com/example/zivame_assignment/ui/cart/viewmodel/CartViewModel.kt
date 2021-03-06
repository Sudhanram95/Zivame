package com.example.zivame_assignment.ui.cart.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zivame_assignment.database.CartEntity
import com.example.zivame_assignment.database.DatabaseCallback
import com.example.zivame_assignment.ui.cart.repository.CartRepository
import javax.inject.Inject

class CartViewModel @Inject constructor(val repository: CartRepository) : ViewModel() {

    private val cartListLiveData = MutableLiveData<List<CartEntity>>()
    private val resultLiveData = MutableLiveData<String>()

    fun getCartList() = cartListLiveData
    fun getResult() = resultLiveData

    fun fetchAllItemsInCart() {
        repository.getAllItemsInCartTable(object : DatabaseCallback {
            override fun onSuccess(response: Any) {
                if (response is List<*>) {
                    cartListLiveData.value = response.filterIsInstance<CartEntity>()
                }
            }

            override fun onFailure() {
                resultLiveData.value = "Could not get Items!"
            }
        })
    }

    fun removeItemFromCart(itemId: Int) {
        repository.removeItemFromCartTable(itemId)
        resultLiveData.value = "Gadget removed from cart"
    }
}