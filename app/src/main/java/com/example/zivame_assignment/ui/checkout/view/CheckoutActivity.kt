package com.example.zivame_assignment.ui.checkout.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.zivame_assignment.R
import com.example.zivame_assignment.application.viewmodel.ViewModelFactory
import com.example.zivame_assignment.ui.checkout.viewmodel.CheckoutViewModel
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_checkout.*
import javax.inject.Inject

class CheckoutActivity : DaggerAppCompatActivity() {

    lateinit var checkoutViewModel: CheckoutViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        checkoutViewModel = ViewModelProvider(this).get(CheckoutViewModel::class.java)
        checkoutViewModel.startCountDown()
    }

    override fun onStart() {
        super.onStart()
        observeViewModel()
    }

    private fun observeViewModel() {
        checkoutViewModel.getShowLoading().observe(this, Observer {
            Log.e("CheckoutActivity", "Checking: $it")
            if (it) {
                llProcessing.visibility = View.VISIBLE
                llCongratulation.visibility = View.GONE
            } else {
                llProcessing.visibility = View.GONE
                llCongratulation.visibility = View.VISIBLE
            }
        })
    }

    override fun onStop() {
        super.onStop()
        checkoutViewModel.getShowLoading().removeObservers(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        checkoutViewModel.cancelTimer()
    }
}