package com.example.zivame_assignment.ui.checkout.repository

import com.example.zivame_assignment.database.CartDao
import com.example.zivame_assignment.database.DatabaseCallback
import java.util.concurrent.ExecutorService
import javax.inject.Inject

class CheckoutRepository @Inject constructor(val executorService: ExecutorService,
                                             val cartDao: CartDao?) {

    fun deleteAllItemsFromCart(databaseCallback: DatabaseCallback) {
        executorService.execute {
            if (cartDao != null) {
                cartDao.deleteAllItem()
                databaseCallback.onSuccess("Deleted successfully")
            } else {
                databaseCallback.onFailure()
            }
        }
    }
}