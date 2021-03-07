package com.example.zivame_assignment.ui.cart.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zivame_assignment.R
import com.example.zivame_assignment.application.viewmodel.ViewModelFactory
import com.example.zivame_assignment.database.CartEntity
import com.example.zivame_assignment.ui.cart.viewmodel.CartViewModel
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_cart.*
import javax.inject.Inject

class CartActivity : DaggerAppCompatActivity(), RemoveItemListener {

    @Inject lateinit var viewModelFactory: ViewModelFactory
    lateinit var cartViewModel: CartViewModel
    lateinit var cartAdapter: CartAdapter

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
            cartAdapter = CartAdapter(this, it.toMutableList())
            rvCart.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            rvCart.adapter = cartAdapter
        })

        cartViewModel.getResult().observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        cartViewModel.getTotalAmount()?.observe(this, Observer {
            btnCheckout.text = "Checkout - Rs.$it"
        })
    }

    override fun onRemoveItemFromCart(position: Int, cartEntity: CartEntity) {
        cartViewModel.removeItemFromCart(cartEntity.itemId)
        if (this::cartAdapter.isInitialized) {
            cartAdapter.removeItemFromCart(position, cartEntity)
        }
    }

    override fun onStop() {
        super.onStop()

        cartViewModel.getCartList().removeObservers(this)
        cartViewModel.getResult().removeObservers(this)
        cartViewModel.getTotalAmount()?.removeObservers(this)
    }
}