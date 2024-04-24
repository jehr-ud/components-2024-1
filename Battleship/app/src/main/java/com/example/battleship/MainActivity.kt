package com.example.battleship

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
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
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        val userId = sharedPreferences.getString("userId", "")

        if (!userId.isNullOrEmpty()) {
                goToGame()
        }

        binding.btnPlay.setOnClickListener {
            val email = binding.txtEmail.text.toString()
            val password = binding.txtPassword.text.toString()

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Log.d("login-ud", "createUserWithEmail:success")
                        val user = auth.currentUser
                        user?.let {
                            saveUserData(user.uid, user.email)
                        }

                        goToGame()

                    } else {
                        Toast.makeText(
                            this,
                            getString(R.string.main_activity_incorrect_login),
                            Toast.LENGTH_LONG
                        ).show()
                        Log.w("login-ud", "createUserWithEmail:failure", task.exception)
                    }
                }
        }
    }

    private fun saveUserData(userId: String, email: String?) {
        val editor = sharedPreferences.edit()
        editor.putString("userId", userId)
        email?.let { editor.putString("email", it) }
        editor.apply()
    }

    private fun goToGame(){
        startActivity(Intent(this, MatchActivity::class.java))
        finish()
    }
}
