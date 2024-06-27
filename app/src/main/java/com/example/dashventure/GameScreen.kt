package com.example.dashventure

import android.animation.ObjectAnimator
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class GameScreen : AppCompatActivity() {
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_game_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize and play the landing sound
        mediaPlayer = MediaPlayer.create(this, R.raw.ag)
        mediaPlayer?.start()

        val imageView = findViewById<ImageView>(R.id.wolfImageView)

        // Start spinning animation
        spinImage(imageView)

        val choiceButton1 = findViewById<Button>(R.id.choice)
        choiceButton1.setOnClickListener {
            val decision = Intent(this, AdventurerGuild::class.java)
            startActivity(decision)
        }

        val choiceButton2 = findViewById<Button>(R.id.back1)
        choiceButton2.setOnClickListener {
            val decision = Intent(this, TitleScreen::class.java)
            startActivity(decision)
        }
    }

    private fun spinImage(imageView: ImageView) {
        val rotateAnimator = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 360f)
        rotateAnimator.duration = 1000 // Duration in milliseconds
        rotateAnimator.interpolator = AccelerateDecelerateInterpolator()
        rotateAnimator.start()
    }

    override fun onPause() {
        super.onPause()
        // Stop the media player if it is playing
        mediaPlayer?.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        // Release the media player resources
        mediaPlayer?.release()
        mediaPlayer = null
    }
}
