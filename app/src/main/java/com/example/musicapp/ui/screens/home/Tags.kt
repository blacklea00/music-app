package com.example.musicapp.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.musicapp.R
import com.example.musicapp.ui.components.MenuTag

@Composable
fun Tags() {

    val menuList = listOf(
        stringResource(R.string.menu_recommend),
        stringResource(R.string.menu_popular),
        stringResource(R.string.menu_like),
        stringResource(R.string.menu_collect)
    )

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(10.dp)
    ) {
        items(menuList, key = {it}) { menu ->
            MenuTag(menu)
        }
    }

}
