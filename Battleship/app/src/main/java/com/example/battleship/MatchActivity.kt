package com.example.battleship

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.battleship.databinding.ActivityMainBinding
import com.example.battleship.databinding.ActivityMatchBinding

class MatchActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMatchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMatchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnMatch.setOnClickListener{
            if (binding.txtMatchAlias.text.isEmpty()){
                Toast.makeText(this, R.string.match_activity_empty_alias, Toast.LENGTH_SHORT)
            } else {
                storageMatchInDB()
            }
        }
    }

    fun storageMatchInDB(){
        print("Saving .. ")
        var intent = Intent(this, GameActivity::class.java)
        startActivity(intent)
    }
}