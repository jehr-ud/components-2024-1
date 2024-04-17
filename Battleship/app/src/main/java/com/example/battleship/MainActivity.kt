package com.example.battleship

import android.content.Context
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

        val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return

        val loggedUser = sharedPref.getString(getString(R.string.user_logged), null)

        if (!loggedUser.isNullOrEmpty()) {
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

                        Log.d("login_user", user.toString())

                        val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: null

                        if (sharedPref != null) {
                            with (sharedPref.edit()) {
                                putString(getString(R.string.user_logged), user.toString())
                                apply()
                            }
                        }

                        goToGame()
                    } else {
                        Log.w("login-ud", "createUserWithEmail:failure", task.exception)
                    }
                }
        }
    }

    private fun goToGame(){
        var intent = Intent(this, MatchActivity::class.java)
        startActivity(intent)
    }
}
