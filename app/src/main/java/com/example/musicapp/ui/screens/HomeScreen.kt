package com.example.musicapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musicapp.R
import com.example.musicapp.ui.components.MenuTag

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

    val menuScrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
    ) {
        // display top menu
        Row(modifier = Modifier.horizontalScroll(menuScrollState)) {

            MenuTag(stringResource(R.string.menu_recommend))

            Spacer(Modifier.padding(horizontal = 5.dp))

            MenuTag(stringResource(R.string.menu_popular))

            Spacer(Modifier.padding(horizontal = 5.dp))

            MenuTag(stringResource(R.string.menu_like))

            Spacer(Modifier.padding(horizontal = 5.dp))

            MenuTag(stringResource(R.string.menu_collect))

        }

        Text(
            text = "Quick picks",
            style = TextStyle(
                color = colorResource(R.color.text_black),
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold
            )
        )
    }
}

