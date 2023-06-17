package com.example.jumpingminds_assignments.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.jumpingminds_assignments.R

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        // Using a Handler to delay the start of the next activity
        Handler(Looper.getMainLooper()).postDelayed({
            // Start the next activity after the delay
            val intent = Intent(this, NewsActivity::class.java)
            startActivity(intent)
            finish() // Optional, to close the splash screen activity
        }, 1500)
    }
}