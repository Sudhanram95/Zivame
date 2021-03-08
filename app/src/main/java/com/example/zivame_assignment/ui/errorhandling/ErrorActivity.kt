package com.example.zivame_assignment.ui.errorhandling

import android.content.Intent
import android.os.Bundle
import com.example.zivame_assignment.R
import com.example.zivame_assignment.ui.gadgetlist.view.GadgetListActivity
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_error.*

class ErrorActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_error)

        val errorMessage = intent.getStringExtra("ErrorMessage") ?: "Something went wrong"

        txtErrorMsg.text = errorMessage

        btnRetry.setOnClickListener {
            val intent = Intent(this, GadgetListActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}