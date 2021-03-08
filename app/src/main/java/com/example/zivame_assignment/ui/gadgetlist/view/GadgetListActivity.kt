package com.example.zivame_assignment.ui.gadgetlist.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.zivame_assignment.R
import com.example.zivame_assignment.application.viewmodel.ViewModelFactory
import com.example.zivame_assignment.network.NetworkState
import com.example.zivame_assignment.ui.cart.view.CartActivity
import com.example.zivame_assignment.ui.gadgetlist.model.ProductModel
import com.example.zivame_assignment.ui.gadgetlist.viewmodel.GadgetListViewModel
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_gadget_list.*
import javax.inject.Inject

class GadgetListActivity : DaggerAppCompatActivity(), AddToCartListener {

    @Inject lateinit var viewModelFactory: ViewModelFactory
    lateinit var gadgetListViewModel: GadgetListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gadget_list)

        gadgetListViewModel = ViewModelProvider(this, viewModelFactory).get(GadgetListViewModel::class.java)

        initializeView()
        observeViewModel()
    }

    private fun initializeView() {
        gadgetListViewModel.fetchAllGadgets()

        relCart.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }
    }

    private fun observeViewModel() {
        gadgetListViewModel.getGadgetList().observe(this, Observer {
            when(it) {
                is NetworkState.Loading -> {
                    //TODO: Loading implementation
                }

                is NetworkState.Success -> {
                    val gadgetAdapter = GadgetAdapter(this, it.data)
                    rvGadgets.layoutManager = GridLayoutManager(this, 2)
                    rvGadgets.adapter = gadgetAdapter
                }

                is NetworkState.Error -> {
                    //TODO: Network error handling
                }
            }
        })

        gadgetListViewModel.getToastMessage().observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        gadgetListViewModel.getBadgeCount()?.observe(this, Observer {
            txtBadgeCount.text = it.toString()
        })
    }

    override fun onAddToCart(id: Int, productModel: ProductModel) {
        val cartEntity = gadgetListViewModel.createCartEntity(id, productModel)
        gadgetListViewModel.addGadgetToCart(cartEntity)
    }

    override fun onDestroy() {
        super.onDestroy()
        gadgetListViewModel.getGadgetList().removeObservers(this)
        gadgetListViewModel.getToastMessage().removeObservers(this)
        gadgetListViewModel.getBadgeCount()?.removeObservers(this)
    }
}