package com.example.dashventure

import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale

class TextToSpeech : AppCompatActivity() {

    lateinit var tts : TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_text_to_speech)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val b1 = findViewById<Button>(R.id.btnSpeak)
        val e1 = findViewById<EditText>(R.id.edText)


        val startButton = findViewById<Button>(R.id.btn2)

        startButton.setOnClickListener{
            val intent = Intent(this, TitleScreen::class.java)
            startActivity(intent)
        }

        b1.setOnClickListener {
            tts = TextToSpeech(applicationContext, TextToSpeech.OnInitListener {
                if(it==TextToSpeech.SUCCESS) {
                    tts.language = Locale.US
                    tts.setSpeechRate(1.0f)
                    tts.speak(e1.text.toString(), TextToSpeech.QUEUE_ADD, null)
                }
            })
        }
    }
}