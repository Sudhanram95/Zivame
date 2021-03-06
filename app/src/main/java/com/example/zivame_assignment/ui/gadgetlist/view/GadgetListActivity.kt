package com.example.zivame_assignment.ui.gadgetlist.view

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.zivame_assignment.R
import com.example.zivame_assignment.application.viewmodel.ViewModelFactory
import com.example.zivame_assignment.network.NetworkState
import com.example.zivame_assignment.ui.cart.view.CartActivity
import com.example.zivame_assignment.ui.gadgetlist.viewmodel.GadgetListViewModel
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_gadget_list.*
import javax.inject.Inject

class GadgetListActivity : DaggerAppCompatActivity() {

    @Inject lateinit var viewModelFactory: ViewModelFactory
    lateinit var gadgetListViewModel: GadgetListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gadget_list)

        gadgetListViewModel = ViewModelProvider(this, viewModelFactory).get(GadgetListViewModel::class.java)

        initializeView()
    }

    override fun onStart() {
        super.onStart()
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
                is NetworkState.Loading -> {}

                is NetworkState.Success -> {
                    val gadgetAdapter = GadgetAdapter(this, it.data)
                    rvGadgets.layoutManager = GridLayoutManager(this, 2)
                    rvGadgets.adapter = gadgetAdapter
                }

                is NetworkState.Error -> {}
            }
        })
    }

    override fun onStop() {
        super.onStop()
        gadgetListViewModel.getGadgetList().removeObservers(this)
    }
}