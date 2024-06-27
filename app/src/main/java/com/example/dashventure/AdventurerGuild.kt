package com.example.dashventure

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
import androidx.core.animation.doOnEnd
import android.animation.ObjectAnimator

class AdventurerGuild : AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_adventurer_guild)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize and play the landing sound
        mediaPlayer = MediaPlayer.create(this, R.raw.questboard)
        mediaPlayer?.start()

        val stopMediaPlayer = {
            mediaPlayer?.stop()
            mediaPlayer?.release()
            mediaPlayer = null
        }

        val imageView = findViewById<ImageView>(R.id.imageView)

        // Start spinning animation
        spinImage(imageView)

        val button1 = findViewById<Button>(R.id.questBoardButton1)
        val button2 = findViewById<Button>(R.id.questBoardButton2)

        button1.setOnClickListener {
            stopMediaPlayer()
            val intent = Intent(this, StolenArtifact::class.java)
            startActivity(intent)
        }

        button2.setOnClickListener {
            stopMediaPlayer()
            val intent = Intent(this, DemonKingLair::class.java)
            startActivity(intent)
        }
    }

    private fun spinImage(imageView: ImageView) {
        val rotateAnimator = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 360f)
        rotateAnimator.duration = 2000 // Duration in milliseconds
        rotateAnimator.interpolator = AccelerateDecelerateInterpolator()
        rotateAnimator.repeatCount = ObjectAnimator.INFINITE // Repeat the animation infinitely
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
