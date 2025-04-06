package com.example.musicapp.data.model

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.musicapp.constants.Constants

data class NavItem(
    val key: Constants.Navigation,        // unique key
    val title: Int,                       // exp: R.string.title
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)
