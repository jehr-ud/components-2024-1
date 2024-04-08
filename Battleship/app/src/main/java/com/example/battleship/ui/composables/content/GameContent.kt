package com.example.battleship.ui.composables.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun GameContent(modifier: Modifier = Modifier) {
    LazyColumn(modifier) {
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