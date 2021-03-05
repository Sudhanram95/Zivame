package com.example.zivame_assignment.application

import android.content.Context
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class ZivameApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()

        instance = this
    }

    companion object {
        private var instance: ZivameApplication? = null
        fun getApplicationContext(): Context {
            return instance!!.applicationContext
        }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.builder().application(this).build()
    }
}