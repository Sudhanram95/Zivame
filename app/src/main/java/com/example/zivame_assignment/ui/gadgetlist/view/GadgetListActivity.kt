package com.example.zivame_assignment.ui.gadgetlist.view

import android.os.Bundle
import com.example.zivame_assignment.R
import dagger.android.support.DaggerAppCompatActivity

class GadgetListActivity : DaggerAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gadget_list)
    }
}