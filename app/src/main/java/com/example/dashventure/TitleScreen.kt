package com.example.dashventure

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class TitleScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_title_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val startButton = findViewById<Button>(R.id.startButton)

        startButton.setOnClickListener{
            val intent = Intent(this, GameScreen::class.java)
            startActivity(intent)
        }

        val fl = findViewById<Button>(R.id.flashLight)

        fl.setOnClickListener{
            val intent = Intent(this, FlashLightActivity::class.java)
            startActivity(intent)
        }

        val ttsb = findViewById<Button>(R.id.tts)

        ttsb.setOnClickListener{
            val intent = Intent(this, TextToSpeech::class.java)
            startActivity(intent)
        }

        val exitButton: Button = findViewById(R.id.exitButton)
        exitButton.setOnClickListener {
            finishAffinity() // This closes all activities and exits the app
        }


    }
}