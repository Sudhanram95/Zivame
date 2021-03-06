package com.example.zivame_assignment.ui.cart.view

import android.os.Bundle
import com.example.zivame_assignment.R
import dagger.android.support.DaggerAppCompatActivity

class CartActivity : DaggerAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
    }
}