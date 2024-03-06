package com.example.battleship

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnPlay = findViewById<View>(R.id.btnPlay) as Button

        btnPlay.setOnClickListener {
            var intent = Intent(this, Gamectivity::class.java)
            startActivity(intent)
        }
    }
}
