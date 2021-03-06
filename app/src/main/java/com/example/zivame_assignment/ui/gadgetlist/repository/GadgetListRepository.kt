package com.example.zivame_assignment.ui.gadgetlist.repository

import androidx.lifecycle.MutableLiveData
import com.example.zivame_assignment.application.ZivameApplication
import com.example.zivame_assignment.database.CartEntity
import com.example.zivame_assignment.database.DatabaseCallback
import com.example.zivame_assignment.database.ZivameDatabase
import com.example.zivame_assignment.network.NetworkCallback
import com.example.zivame_assignment.ui.gadgetlist.model.GadgetListResponse
import com.example.zivame_assignment.ui.gadgetlist.model.ProductModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GadgetListRepository @Inject constructor(val apiService: GadgetListApiService) {
    var db: ZivameDatabase? = null
    init {
        db = ZivameDatabase.getDatabase(ZivameApplication.getApplicationContext())
    }

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

    fun addGadgetToCart(productId: Int, productModel: ProductModel, databaseCallback: DatabaseCallback) {
        ZivameDatabase.databaseWriteExecutor.execute {
            val cartEntity = CartEntity()
            cartEntity.apply {
                itemId = productId
                itemName = productModel.name
                price = productModel.price
                imageUrl = productModel.imageUrl
            }

            db?.let {
                val cartDao = it.cartDao()
                databaseCallback.onAddedToTableResult(cartDao.addItemToCart(cartEntity))
            }
        }
    }
}