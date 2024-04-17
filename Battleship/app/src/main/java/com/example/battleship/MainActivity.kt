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

        val loggedUser = sharedPref.getString(getString(R.string.user_logged_uid), null)

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

                        val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: null

                        if (user != null && sharedPref != null) {
                            Log.d("login_user_uid", user.uid)
                            Log.d("login_user_email", user.email.toString())

                            with (sharedPref.edit()) {
                                putString(getString(R.string.user_logged_uid), user.uid)
                                putString(getString(R.string.user_logged_email), user.email.toString())
                                apply()
                            }

                            goToGame()
                        } else {
                            Toast.makeText(
                                this,
                                getString(R.string.main_activity_error_storage_login),
                                Toast.LENGTH_LONG
                            ).show()
                        }
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

    private fun goToGame(){
        var intent = Intent(this, MatchActivity::class.java)
        startActivity(intent)
    }
}
