package com.example.opa_android2

import android.app.Application
import android.util.Log

class ProdutoApplication : Application() {
    private val TAG = "ProdutoApplication"

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object{
        private var appInstance: ProdutoApplication? = null
        fun getInstance(): ProdutoApplication {
            if (appInstance == null) {
                throw IllegalStateException("Configure a classe application no Android Manifest.xml")
            }
            return appInstance!!
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        Log.d(TAG, "ProdutoApplication.onTerminate()")
    }

}