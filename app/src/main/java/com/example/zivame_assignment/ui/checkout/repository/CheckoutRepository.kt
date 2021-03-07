package com.example.zivame_assignment.ui.checkout.repository

import com.example.zivame_assignment.database.DatabaseCallback
import com.example.zivame_assignment.database.ZivameDatabase
import javax.inject.Inject

class CheckoutRepository @Inject constructor(val dbInstance: ZivameDatabase?) {

    fun deleteAllItemsFromCart(databaseCallback: DatabaseCallback) {
        ZivameDatabase.databaseWriteExecutor.execute {
            val cartDao = dbInstance?.cartDao()
            if (cartDao != null) {
                cartDao.deleteAllItem()
                databaseCallback.onSuccess("Deleted successfully")
            } else {
                databaseCallback.onFailure()
            }
        }
    }
}