package com.example.battleship

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.battleship.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnPlay.setOnClickListener {
            val email = binding.txtEmail.toString()
            val password = binding.txtPassword.toString()
            Toast.makeText(this, email, Toast.LENGTH_LONG)
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Log.d("login-ud", "createUserWithEmail:success")
                        val user = auth.currentUser
                        goToGame()
                    } else {
                        Log.w("login-ud", "createUserWithEmail:failure", task.exception)
                    }
                }
        }
    }

    fun goToGame(){
        var intent = Intent(this, MatchActivity::class.java)
        startActivity(intent)
    }
}
