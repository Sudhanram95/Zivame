package com.example.zivame_assignment.ui.checkout.repository

import com.example.zivame_assignment.database.CartDao
import com.example.zivame_assignment.database.DatabaseCallback
import com.example.zivame_assignment.database.ZivameDatabase
import javax.inject.Inject

class CheckoutRepository @Inject constructor(val cartDao: CartDao?) {

    fun deleteAllItemsFromCart(databaseCallback: DatabaseCallback) {
        ZivameDatabase.databaseWriteExecutor.execute {
            if (cartDao != null) {
                cartDao.deleteAllItem()
                databaseCallback.onSuccess("Deleted successfully")
            } else {
                databaseCallback.onFailure()
            }
        }
    }
}