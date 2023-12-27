package com.example.myapplicationskripsiiqbal3

import android.app.Application
import android.location.Location
import androidx.appcompat.app.AppCompatDelegate
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class MyApplication : Application(){
    var mIsLogin = false
    var mLastLocation: Location? = null
    companion object {
        lateinit var instance: MyApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

    }
}