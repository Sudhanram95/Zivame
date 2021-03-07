package com.example.zivame_assignment.ui.gadgetlist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zivame_assignment.database.CartEntity
import com.example.zivame_assignment.database.DatabaseCallback
import com.example.zivame_assignment.network.NetworkCallback
import com.example.zivame_assignment.network.NetworkState
import com.example.zivame_assignment.ui.gadgetlist.model.GadgetListResponse
import com.example.zivame_assignment.ui.gadgetlist.model.ProductModel
import com.example.zivame_assignment.ui.gadgetlist.repository.GadgetListRepository
import javax.inject.Inject

class GadgetListViewModel @Inject
constructor(val repository: GadgetListRepository) : ViewModel() {

    private val gadgetListLiveData = MutableLiveData<NetworkState<List<ProductModel>>>()
    private val toastMessageLiveData = MutableLiveData<String>()

    fun getGadgetList() = gadgetListLiveData
    fun getToastMessage() = toastMessageLiveData
    fun getBadgeCount() = repository.getBadgeCountFromDb()

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

    fun createCartEntity(productId: Int, productModel: ProductModel): CartEntity {
        val cartEntity = CartEntity()
        cartEntity.apply {
            itemId = productId
            itemName = productModel.name
            price = productModel.price
            imageUrl = productModel.imageUrl
        }
        return cartEntity
    }

    fun addGadgetToCart(cartEntity: CartEntity) {
        repository.addGadgetToCart(cartEntity, object : DatabaseCallback {
            override fun onSuccess(response: Any) {
                toastMessageLiveData.postValue("Added to cart successfully")
            }

            override fun onFailure() {
                toastMessageLiveData.postValue("Already added to cart")
            }
        })
    }
}