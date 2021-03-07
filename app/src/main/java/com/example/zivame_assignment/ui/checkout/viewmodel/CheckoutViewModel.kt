package com.example.zivame_assignment.ui.checkout.viewmodel

import android.os.CountDownTimer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zivame_assignment.database.DatabaseCallback
import com.example.zivame_assignment.ui.checkout.repository.CheckoutRepository
import javax.inject.Inject

class CheckoutViewModel @Inject constructor(val repository: CheckoutRepository) : ViewModel() {

    lateinit var countDownTimer: CountDownTimer

    private val showLoadingLiveData = MutableLiveData<Boolean>()
    private val removeAllItemsLiveData = MutableLiveData<Boolean>()

    fun getShowLoading() = showLoadingLiveData
    fun getRemoveAllItems() = removeAllItemsLiveData

    fun startCountDown() {
        countDownTimer = object : CountDownTimer(10*1000, 1000) {
            override fun onTick(p0: Long) {
                if (showLoadingLiveData.value == null)
                    showLoadingLiveData.value = true
            }

            override fun onFinish() {
                removeAllItemsLiveData.value = true
            }
        }

        countDownTimer.start()
    }

    fun cancelTimer() {
        if(this::countDownTimer.isInitialized) {
            countDownTimer.cancel()
        }
    }

    fun removeAllItemsFromCart() {
        repository.deleteAllItemsFromCart(object : DatabaseCallback {
            override fun onSuccess(response: Any) {
                showLoadingLiveData.value = false
            }

            override fun onFailure() {

            }
        })
    }
}