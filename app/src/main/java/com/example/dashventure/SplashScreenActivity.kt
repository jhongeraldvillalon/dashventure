package com.example.dashventure

import android.animation.ObjectAnimator
import android.content.Intent
import android.icu.text.CaseMap.Title
import android.media.Image
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val swordbrandish = findViewById<ImageView>(R.id.sword_brand)

        swordbrandish.alpha = 0f
        // Create a fade-in animation
        val fadeIn = swordbrandish.animate().setDuration(1500).alpha(1f)

        // Create a spin animation
        val spin = ObjectAnimator.ofFloat(swordbrandish, "rotation", 0f, 360f).apply {
            duration = 1500
        }

        // Start both animations
        fadeIn.withEndAction {
            val i = Intent(this, TitleScreen::class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }

        // Start the spin animation
        spin.start()
    }
}