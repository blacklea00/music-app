package com.example.musicapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LibraryMusic
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LibraryMusic
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musicapp.R
import com.example.musicapp.constants.Constants
import com.example.musicapp.data.model.NavItem
import com.example.musicapp.ui.components.AnimatedPlayer
import com.example.musicapp.ui.components.BottomBar
import com.example.musicapp.ui.components.PlayerState
import com.example.musicapp.ui.components.TopBar

@Composable
fun MainScreen(
    modifier: Modifier = Modifier
){
    val barTextColor = TextStyle(color = colorResource(R.color.text_pink), fontSize = 14.sp)

    var selectedBar by remember{ mutableStateOf(Constants.Navigation.HOME) }

    val barList = listOf(
        NavItem(key = Constants.Navigation.HOME, title = R.string.bottom_bar_home, selectedIcon = Icons.Filled.Home, unselectedIcon = Icons.Outlined.Home),
        NavItem(key = Constants.Navigation.EXPLORE, title = R.string.bottom_bar_explore, selectedIcon = Icons.Filled.Explore, unselectedIcon = Icons.Outlined.Explore),
        NavItem(key = Constants.Navigation.LIBRARY, title = R.string.bottom_bar_library, selectedIcon = Icons.Filled.LibraryMusic, unselectedIcon = Icons.Outlined.LibraryMusic),
        NavItem(key = Constants.Navigation.PROFILE, title = R.string.bottom_bar_profile, selectedIcon = Icons.Filled.Person, unselectedIcon = Icons.Outlined.Person)
    )

    val homeScrollState = rememberScrollState()

    var playerState by remember { mutableStateOf(PlayerState.COLLAPSED) }

    Box(modifier = Modifier
        .fillMaxSize()) {


        AnimatedPlayer(
            state = playerState,
            onCollapse = { playerState = PlayerState.COLLAPSED },
            onExpand = { playerState = PlayerState.EXPANDED }
        )

        Scaffold(
            topBar = {
                TopBar()
            },
            bottomBar = {

                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = colorResource(R.color.white))
                            .padding(15.dp),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {

                        barList.forEach { item ->
                            BottomBar(
                                item = item,
                                barTextColor = barTextColor,
                                isSelected = selectedBar == item.key,
                                onClick = { selectedBar = item.key }
                            )
                        }

                    }

                    Box(
                        modifier = Modifier
                            .height(8.dp)
                            .background(color = colorResource(R.color.bar_color))
                            .fillMaxWidth()
                    )
                }

            },
            containerColor = colorResource(R.color.background_color),
        ) { innerPadding ->


            Column(
                modifier = modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                if (Constants.Navigation.HOME == selectedBar) {
                    HomeScreen(homeScrollState = homeScrollState, onExpandPlayer = {
                        playerState = PlayerState.EXPANDED
                    })
                } else {
                    Text(
                        text = "Preparing",
                        style = TextStyle(
                            color = colorResource(R.color.white),
                            fontSize = 23.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }

            }

        }
    }
}