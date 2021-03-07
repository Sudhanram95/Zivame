package com.example.zivame_assignment.ui.cart.repository

import androidx.lifecycle.LiveData
import com.example.zivame_assignment.database.CartDao
import com.example.zivame_assignment.database.DatabaseCallback
import com.example.zivame_assignment.database.ZivameDatabase
import javax.inject.Inject

class CartRepository @Inject constructor(val cartDao: CartDao?) {

    fun getAllItemsInCartTable(databaseCallback: DatabaseCallback) {
        ZivameDatabase.databaseWriteExecutor.execute {
            if (cartDao != null)
                databaseCallback.onSuccess(cartDao.getAllItemsInCart())
            else
                databaseCallback.onFailure()
        }
    }

    fun removeItemFromCartTable(itemId: Int, databaseCallback: DatabaseCallback) {
        ZivameDatabase.databaseWriteExecutor.execute {
            if (cartDao != null) {
                cartDao.removeFromCart(itemId)
                databaseCallback.onSuccess("Item removed successfully")
            } else {
                databaseCallback.onFailure()
            }
        }
    }

    fun getTotalAmount(): LiveData<Int>? {
        cartDao?.let {
            return it.getTotalAmount()
        }
        return null
    }
}