package com.d121211086.Doaapp.main

import android.app.Application
import  com.d121211086.Doaapp.data.AppContainer
import  com.d121211086.Doaapp.data.DefaultAppContainer

class MyApplication: Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }

}