package com.example.battleship.model

data class MenuItem(
    val id: ScreensRoute,
    val textId: Int
)

val drawerScreens = listOf(
    MenuItem(ScreensRoute.SCREEN_1, R.string.screen_1),
    MenuItem(ScreensRoute.SCREEN_2, R.string.screen_2),
)

enum class ScreensRoute {
    SCREEN_1, SCREEN_2
}