package com.example.battleship

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.battleship.databinding.ActivityMainBinding
import com.example.battleship.databinding.ActivityMatchBinding
import com.example.battleship.logic.Board
import com.example.battleship.logic.Game
import com.example.battleship.logic.Player
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MatchActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMatchBinding
    private lateinit var database: DatabaseReference
    private lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMatchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = Firebase.database.reference
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)


        binding.btnMatch.setOnClickListener{
            if (binding.txtMatchAlias.text.isEmpty()){
                Toast.makeText(this, R.string.match_activity_empty_alias, Toast.LENGTH_SHORT)
            } else {
                storageMatchInDB()
            }
        }
    }

    fun storageMatchInDB(){
        val userId = sharedPreferences.getString("userId", "")
        val email = sharedPreferences.getString("email", "")

        if (userId.isNullOrEmpty() || email.isNullOrEmpty()){
            goToLogin()
            return
        }

        val alias = binding.txtMatchAlias.text.toString()

        database.child("games").orderByChild("aliasMatch").equalTo(alias)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        for (childSnapshot in dataSnapshot.children) {
                            val gameId = childSnapshot.key
                            gameId?.let {
                                val gameSnapshot = childSnapshot.getValue(Game::class.java)
                                gameSnapshot?.let { game ->
                                    game.player2 = Player(email, userId)
                                    game.canStart = true
                                    database.child("games").child(it).setValue(game)
                                    goToGame(gameId)
                                }
                            }
                        }
                    } else {
                        val gameID = database.child("games").push().key
                        gameID?.let {
                            val rows = 10
                            val cols = 10
                            val board = Board()
                            val player1 = Player(email,  userId)
                            val player2 = null

                            val game = Game(rows, cols, board, alias, false, userId, player1, player2, "")
                            game.generateCells()
                            game.generateShips()

                            database.child("games").child(it).setValue(game)
                            setupGameUpdateListener(gameID)

                            Toast.makeText(this@MatchActivity, "Esperando jugador 2..", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                override fun onCancelled(databaseError: DatabaseError) {
                    // Handle any errors
                    Toast.makeText(this@MatchActivity, "Error: ${databaseError.message}", Toast.LENGTH_SHORT).show()
                }
            })
    }

    fun setupGameUpdateListener(gameId: String) {
        val database = database.child("games").child(gameId)

        val gameUpdateListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Handle data changes
                val game = dataSnapshot.getValue(Game::class.java)
                game?.let {
                    if (game.canStart){
                        goToGame(gameId)
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
            }
        }

        database.addValueEventListener(gameUpdateListener)
    }

    fun goToLogin(){
        var intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun goToGame(gameId: String){
        val intent = Intent(this@MatchActivity, GameActivity::class.java)
        intent.putExtra("gameId", gameId)
        startActivity(intent)
    }
}