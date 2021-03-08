package com.example.zivame_assignment.ui.cart.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zivame_assignment.application.viewmodel.SingleLiveEvent
import com.example.zivame_assignment.database.CartEntity
import com.example.zivame_assignment.database.DatabaseCallback
import com.example.zivame_assignment.ui.cart.repository.CartRepository
import javax.inject.Inject

class CartViewModel @Inject constructor(val repository: CartRepository) : ViewModel() {

    private val cartListLiveData = MutableLiveData<List<CartEntity>>()
    private val resultLiveData = SingleLiveEvent<String>()
    private val showEmptyCartLiveData = SingleLiveEvent<Boolean>()

    fun getCartList(): LiveData<List<CartEntity>> = cartListLiveData
    fun getResult(): LiveData<String> = resultLiveData
    fun getShowEmptyCart() = showEmptyCartLiveData
    fun getTotalAmount(): LiveData<Int>? = repository.getTotalAmount()

    fun fetchAllItemsInCart() {
        repository.getAllItemsInCartTable(object : DatabaseCallback {
            override fun onSuccess(response: Any) {
                if (response is List<*>) {
                    if (response.isEmpty())
                        showEmptyCartLiveData.postValue(true)

                    cartListLiveData.postValue(response.filterIsInstance<CartEntity>())
                }
            }

            override fun onFailure() {
                resultLiveData.postValue( "Could not get Items!")
            }
        })
    }

    fun removeItemFromCart(itemId: Int) {
        repository.removeItemFromCartTable(itemId, object : DatabaseCallback {
            override fun onSuccess(response: Any) {
                resultLiveData.postValue(response as String)
            }

            override fun onFailure() {
                resultLiveData.postValue( "Could not remove item!")
            }
        })
    }

    fun checkIfCartIsEmpty(totalAmount: Int?) {
        if (totalAmount == null) {
            showEmptyCartLiveData.value = true
        }
    }
}