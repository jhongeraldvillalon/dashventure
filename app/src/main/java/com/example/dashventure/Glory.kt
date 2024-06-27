package com.example.dashventure

import android.content.Intent
import android.icu.text.CaseMap.Title
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Glory : AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_glory)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
// Initialize and play the landing sound
        mediaPlayer = MediaPlayer.create(this, R.raw.glory)
        mediaPlayer?.start()
        val button1 = findViewById<Button>(R.id.playAgain)
        button1.setOnClickListener{
            val intent = Intent(this, GameScreen::class.java)
            startActivity(intent)
        }

        val button2 = findViewById<Button>(R.id.returnToTileScreen)
        button2.setOnClickListener{
            val intent = Intent(this, TitleScreen::class.java)
            startActivity(intent)
        }
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