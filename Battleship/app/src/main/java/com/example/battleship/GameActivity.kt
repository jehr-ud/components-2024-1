package com.example.battleship

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.battleship.model.Rules.messages
import androidx.compose.ui.unit.dp
import com.example.battleship.model.DrawerItem
import com.example.battleship.ui.composables.menu.DrawerFooter
import kotlinx.coroutines.launch
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.example.battleship.logic.Board
import com.example.battleship.logic.Game
import com.example.battleship.logic.Player
import com.example.battleship.ui.composables.content.GameContent
import com.example.battleship.ui.composables.content.RulesLazy
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class GameActivity : AppCompatActivity() {
    private var game: Game? = null
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        database = Firebase.database.reference

        val gameId = intent.getStringExtra("gameId")
        gameId?.let {
            fetchGameFromDatabase(it)
        } ?: run {                            //asegurar que si no existe el juego se cree uno nuevo
            createNewGame()
        }
    }

    private fun fetchGameFromDatabase(gameId: String) {
        Toast.makeText(this, "Search Game Id: $gameId", Toast.LENGTH_LONG).show()

        database.child("games").child(gameId).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val game = dataSnapshot.getValue(Game::class.java)
                Toast.makeText(this@GameActivity, "Game found", Toast.LENGTH_LONG).show()
                game?.let {
                    updateGame(it)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@GameActivity, "Error retrieving game: ${databaseError.message}", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun createNewGame() {
        val rows = 10
        val cols = 10
        val board = Board(rows, cols)
        val player1 = Player("player1@example.com", "player1Id")
        val player2 = Player("player2@example.com", "player2Id")

        val game = Game(rows, cols, board, "testMatch", false, "player1Id", player1, player2, "")
        game.generateCells()
        game.generateShips()

        database.child("games").push().setValue(game)
        updateGame(game)
    }

    private fun updateGame(game: Game) {
        this.game = game
        if (game.finishAt.isNotBlank()){
            Toast.makeText(this, "Game finished...", Toast.LENGTH_LONG).show()
            goToMatch()
        }

        setContent {
            GameScreen(this.game!!)
        }
    }

    private fun goToMatch(){
        startActivity(Intent(this, MatchActivity::class.java))
        finish()
    }
}


@OptIn(
    ExperimentalAnimationApi::class, ExperimentalComposeUiApi::class,
    ExperimentalMaterial3Api::class
)
@Composable
fun GameScreen(game: Game) {

    val navigationState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()

    val items = listOf(
        DrawerItem(
            title = "Game",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            route = "game"
        ),
        DrawerItem(
            title = "Rules",
            selectedIcon = Icons.Filled.Info,
            unselectedIcon = Icons.Outlined.Info,
            route = "rules"
        )
    )

    val selectedItem = remember { mutableStateOf(items[0]) }

    Surface {
        ModalNavigationDrawer(
            drawerContent = {
                ModalDrawerSheet {
                    Spacer(modifier = Modifier.height(26.dp))
                    Image(
                        painter = painterResource(id = R.drawable.icon_user),
                        contentDescription = "",
                        modifier = Modifier
                            .size(150.dp)
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.height(26.dp))
                    items.forEach { drawerItem ->
                        NavigationDrawerItem(
                            label = { Text(text = drawerItem.title) },
                            selected = drawerItem == selectedItem.value,
                            onClick = {
                                selectedItem.value = drawerItem
                                scope.launch {
                                    navigationState.close()
                                }
                                navController.navigate(drawerItem.route)
                            },
                            icon = {
                                Icon(
                                    imageVector = if (drawerItem == selectedItem.value) {
                                        drawerItem.selectedIcon
                                    } else drawerItem.unselectedIcon,
                                    contentDescription = drawerItem.title
                                )
                            },
                            badge = {
                                drawerItem.badgeCount?.let {
                                    Text(text = it.toString())
                                }
                            },
                            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                        )
                    }
                    DrawerFooter()
                }
            },
            drawerState = navigationState,
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(text = stringResource(R.string.app_name)) },
                        navigationIcon = {
                            IconButton(onClick = {
                                scope.launch {
                                    navigationState.open()
                                }
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Menu,
                                    contentDescription = stringResource(R.string.open_drawer)
                                )
                            }
                        }
                    )
                }
            ) {
                Column(Modifier.padding(it)) {
                    NavHost(navController, startDestination = "game") {
                        composable("game") { GameContent(game) }
                        composable("rules") { RulesLazy(messages) }
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun GamePreview() {
    val rows = 10
    val cols = 10
    val board = Board()
    val player1 = Player("juan", "")
    val player2 = Player("pedro", "")

    val game = Game(rows, cols, board, "test", false, "", player1, player2, "")
    game.generateCells()

    GameScreen(game)
}