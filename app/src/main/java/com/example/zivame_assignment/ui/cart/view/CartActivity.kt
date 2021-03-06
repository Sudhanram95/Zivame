package com.example.zivame_assignment.ui.cart.view

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zivame_assignment.R
import com.example.zivame_assignment.application.viewmodel.ViewModelFactory
import com.example.zivame_assignment.ui.cart.viewmodel.CartViewModel
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_cart.*
import javax.inject.Inject

class CartActivity : DaggerAppCompatActivity() {

    @Inject lateinit var viewModelFactory: ViewModelFactory
    lateinit var cartViewModel: CartViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        cartViewModel = ViewModelProvider(this, viewModelFactory).get(CartViewModel::class.java)
        cartViewModel.fetchAllItemsInCart()
    }

    override fun onStart() {
        super.onStart()
        observeViewModel()
    }

    private fun observeViewModel() {
        cartViewModel.getCartList().observe(this, Observer {
            val cartAdapter = CartAdapter(this, it.toMutableList())
            rvCart.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            rvCart.adapter = cartAdapter
        })

        cartViewModel.getResult().observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

    override fun onStop() {
        super.onStop()

        cartViewModel.getCartList().removeObservers(this)
        cartViewModel.getResult().removeObservers(this)
    }
}