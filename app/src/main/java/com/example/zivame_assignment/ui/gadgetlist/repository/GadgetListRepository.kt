package com.example.zivame_assignment.ui.gadgetlist.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.zivame_assignment.application.ZivameApplication
import com.example.zivame_assignment.database.CartDao
import com.example.zivame_assignment.database.CartEntity
import com.example.zivame_assignment.database.DatabaseCallback
import com.example.zivame_assignment.database.ZivameDatabase
import com.example.zivame_assignment.network.NetworkCallback
import com.example.zivame_assignment.ui.gadgetlist.model.GadgetListResponse
import com.example.zivame_assignment.ui.gadgetlist.model.ProductModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.ExecutorService
import javax.inject.Inject

class GadgetListRepository @Inject constructor(val apiService: GadgetListApiService,
                                               val executor: ExecutorService, val cartDao: CartDao?) {

    fun getAllGadgetsFromApi(networkCallback: NetworkCallback) {
        apiService.getAllGadgetsList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<GadgetListResponse>() {
                override fun onSuccess(response: GadgetListResponse) {
                    networkCallback.onSuccess(response)
                }

                override fun onError(e: Throwable) {
                    networkCallback.onError(e)
                }
            })
    }

    fun addGadgetToCart(cartEntity: CartEntity, databaseCallback: DatabaseCallback) {
        executor.execute {
            cartDao?.let {
                val result = it.addItemToCart(cartEntity)
                if (result > 0) databaseCallback.onSuccess(result)
                else databaseCallback.onFailure()
            }
        }
    }

    fun getBadgeCountFromDb(): LiveData<Int>? {
        cartDao?.let {
            return it.getItemCount()
        }
        return null
    }
}