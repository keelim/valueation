package com.keelim.valueation

import android.app.Application
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.initialization.InitializationStatus
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener
import com.keelim.valueation.utils.AppOpenManager

class MyApplication : Application() {
    private lateinit var appOpenManager: AppOpenManager
    override fun onCreate() {
        super.onCreate()

        MobileAds.initialize(this) { object :OnInitializationCompleteListener{
            override fun onInitializationComplete(p0: InitializationStatus?) {

            }
        }}

        appOpenManager = AppOpenManager(this)

    }
}