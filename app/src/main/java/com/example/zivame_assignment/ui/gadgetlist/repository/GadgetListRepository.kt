package com.example.zivame_assignment.ui.gadgetlist.repository

import com.example.zivame_assignment.network.NetworkCallback
import com.example.zivame_assignment.ui.gadgetlist.model.GadgetListResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GadgetListRepository @Inject constructor(private val apiService: GadgetListApiService) {

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
}