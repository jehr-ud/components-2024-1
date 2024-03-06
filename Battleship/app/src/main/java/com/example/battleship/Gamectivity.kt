package com.example.battleship

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class Gamectivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Board()
        }
    }
}

@Composable
fun Board(){
    LazyColumn {
        items(10) { row ->
            Row {
                repeat(10) { col ->
                    Column {
                        Button(onClick = { /*TODO*/ }) {
                            Text(text = "$col")
                        }
                    }

                }
            }
        }
    }
}

@Preview
@Composable
fun boardView(){
    Board()
}