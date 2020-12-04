package com.keelim.valueation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.keelim.valueation.R
import com.keelim.valueation.databinding.ActivitySplashBinding
import com.keelim.valueation.utils.AppOpenManager

class SplashActivity : AppCompatActivity(R.layout.activity_splash) {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 3000)
    }

}