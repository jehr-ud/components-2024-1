package com.example.battleship

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
            val toast = Toast.makeText(this, "Clic en botón!!", Toast.LENGTH_LONG)
            toast.show()
        }
    }
}
