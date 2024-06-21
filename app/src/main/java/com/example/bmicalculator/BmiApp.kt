package com.example.bmicalculator

import android.app.Application
import com.google.firebase.FirebaseApp

class BmiApp : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }

}