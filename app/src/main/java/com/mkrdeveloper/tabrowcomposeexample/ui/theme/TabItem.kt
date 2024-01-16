package com.mkrdeveloper.tabrowcomposeexample.ui.theme

import androidx.compose.ui.graphics.vector.ImageVector

data class TabItem(
    val title :String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector
)
