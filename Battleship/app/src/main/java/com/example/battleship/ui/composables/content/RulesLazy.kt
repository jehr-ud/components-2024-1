package com.example.battleship.ui.composables.content

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.battleship.model.Message
import com.example.battleship.model.Rules

@Composable
fun RulesLazy(messages: List<Message>) {
    remember {
        Rules.messages
    }
    LazyColumn(
        contentPadding = PaddingValues(top = 60.dp)
    ) {

        items(messages) { message ->
            MyCard(message)
        }
    }
}


@Composable
fun MyCard(message: Message) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier
                .padding(horizontal = 25.dp, vertical = 16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(Color.White),
            elevation = CardDefaults.cardElevation(10.dp)
        ) {
            Column {
                CardImage(message)
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    CardText(message)
                }
            }
        }
    }
}

@Composable
fun CardText(message: Message) {
    Text(
        text = message.title,
        fontSize = 28.sp,
        color = Color(0xFF2334C5),
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Monospace
    )

    Text(
        text = message.body,
        color = Color(0xFF9FA7EC),
        fontSize = 18.sp,
        maxLines = 20,
        overflow = TextOverflow.Clip,
        modifier = Modifier.padding(2.dp),
    )
}

@Composable
fun CardImage(message: Message) {
    Image(
        painter = painterResource(id = message.image),
        contentDescription = "Image",
        modifier = Modifier
            .width(600.dp).fillMaxHeight(),
        contentScale = ContentScale.Crop
    )
}

@Preview
@Composable
fun DefaultPreview() {
    RulesLayout()
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RulesLayout() {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        text = "BattleShip Rules",
                        fontWeight = FontWeight.Bold
                    )
                })
        },
    ) {
        Column {
            RulesLazy(messages = Rules.messages)
        }
    }
}

