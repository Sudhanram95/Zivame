package com.example.zivame_assignment.ui.gadgetlist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zivame_assignment.network.NetworkCallback
import com.example.zivame_assignment.network.NetworkState
import com.example.zivame_assignment.ui.gadgetlist.model.GadgetListResponse
import com.example.zivame_assignment.ui.gadgetlist.model.ProductModel
import com.example.zivame_assignment.ui.gadgetlist.repository.GadgetListRepository
import javax.inject.Inject

class GadgetListViewModel @Inject
constructor(val repository: GadgetListRepository) : ViewModel() {

    private val gadgetListLiveData = MutableLiveData<NetworkState<List<ProductModel>>>()

    fun getGadgetList() = gadgetListLiveData

    fun fetchAllGadgets() {
        gadgetListLiveData.value = NetworkState.Loading()
        repository.getAllGadgetsFromApi(object : NetworkCallback {
            override fun onSuccess(response: Any) {
                val gadgetListResponse = response as GadgetListResponse
                gadgetListResponse.productList?.let {
                    gadgetListLiveData.value = NetworkState.Success(it)
                }
            }

            override fun onError(error: Throwable) {
                gadgetListLiveData.value = NetworkState.Error(error)
            }
        })
    }
}