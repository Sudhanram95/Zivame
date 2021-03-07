package com.example.zivame_assignment.ui.checkout.view

import android.os.Bundle
import com.example.zivame_assignment.R
import dagger.android.support.DaggerAppCompatActivity

class CheckoutActivity : DaggerAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)
    }
}