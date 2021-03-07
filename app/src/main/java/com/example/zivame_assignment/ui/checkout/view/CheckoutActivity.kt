package com.example.zivame_assignment.ui.checkout.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.zivame_assignment.R
import com.example.zivame_assignment.application.viewmodel.ViewModelFactory
import com.example.zivame_assignment.ui.checkout.viewmodel.CheckoutViewModel
import com.example.zivame_assignment.ui.gadgetlist.view.GadgetListActivity
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_checkout.*
import javax.inject.Inject

class CheckoutActivity : DaggerAppCompatActivity() {

    @Inject lateinit var viewModelFactory: ViewModelFactory
    lateinit var checkoutViewModel: CheckoutViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        checkoutViewModel = ViewModelProvider(this, viewModelFactory).get(CheckoutViewModel::class.java)
        initializeView()
    }

    private fun initializeView() {
        checkoutViewModel.startCountDown()
        btnContinueShopping.setOnClickListener {
            val intent = Intent(this, GadgetListActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
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

        checkoutViewModel.getRemoveAllItems().observe(this, Observer {
            if (it) {
                checkoutViewModel.cancelTimer()
                checkoutViewModel.removeAllItemsFromCart()
            }
        })
    }

    override fun onStop() {
        super.onStop()
        checkoutViewModel.getShowLoading().removeObservers(this)
        checkoutViewModel.getRemoveAllItems().removeObservers(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        checkoutViewModel.cancelTimer()
    }

    override fun onBackPressed() {
        //Disabling physical back press
    }
}