package com.example.battleship.model

import androidx.compose.ui.graphics.vector.ImageVector

data class DrawerItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val badgeCount: Int? = null,
    val route: String
)