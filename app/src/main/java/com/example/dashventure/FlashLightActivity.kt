package com.example.dashventure

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.camera2.CameraManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ToggleButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FlashLightActivity : AppCompatActivity() {

    lateinit var tb: ToggleButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_flash_light)

        tb = findViewById(R.id.toggleButton)
        val fl = findViewById<Button>(R.id.flReturn)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tb.isEnabled = false
        var light = false

        fl.setOnClickListener {
            val intent = Intent(this, TitleScreen::class.java)
            startActivity(intent)
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 123)
        } else {
            tb.isEnabled = true
        }

        var cm: CameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        tb.setOnClickListener {

            if(packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
                if(tb.text == "ON") {
                    light = true
                } else {
                    light = false
                }

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    cm.setTorchMode(cm.cameraIdList[0], light)
                }
            }

        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 123) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                tb.isEnabled = true
            } else {
                // Handle the case when permission is denied
            }
        }
    }
}
