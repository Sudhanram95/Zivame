package com.example.zivame_assignment.ui.checkout.viewmodel

import android.os.CountDownTimer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CheckoutViewModel : ViewModel() {

    lateinit var countDownTimer: CountDownTimer

    private val showLoadingLiveData = MutableLiveData<Boolean>()

    fun getShowLoading() = showLoadingLiveData

    fun startCountDown() {
        countDownTimer = object : CountDownTimer(10*1000, 1000) {
            override fun onTick(p0: Long) {
                if (showLoadingLiveData.value == null)
                    showLoadingLiveData.value = true
            }

            override fun onFinish() {
                showLoadingLiveData.value = false
            }
        }

        countDownTimer.start()
    }

    fun cancelTimer() {
        if(this::countDownTimer.isInitialized) {
            countDownTimer.cancel()
        }
    }
}