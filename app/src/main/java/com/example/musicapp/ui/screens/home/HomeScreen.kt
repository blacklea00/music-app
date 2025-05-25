package com.example.musicapp.ui.screens.home

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.musicapp.constants.ImageConstants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeScrollState: ScrollState,
    onExpandPlayer: () -> Unit
) {

    val musicBgList = listOf(
        ImageConstants.IMAGE_1,
        ImageConstants.IMAGE_2,
        ImageConstants.IMAGE_3
    )

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showSheet by remember { mutableStateOf(false) }

    // bottom sheet modal
    if (showSheet) {
        LaunchedEffect(Unit) {
            sheetState.show()
        }
        ModalBottomSheet(
            onDismissRequest = { showSheet = false },
            modifier = Modifier
                .fillMaxWidth(0.95f),
            sheetState = sheetState,
            shape = RoundedCornerShape(10.dp),
            dragHandle = null
        ) {
            SongSetting()
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
            .verticalScroll(homeScrollState),
        verticalArrangement = Arrangement.spacedBy(25.dp)
    ) {
        // display top tags
        Tags()

        // quick picks listing
        QuickPicks(musicBgList, onExpandPlayer)

        // most played
        MostPlayed(onExpandPlayer)

        // special dial
        SpecialDial(onClickHandleMoreSetting = { showSheet = true }, onExpandPlayer)

    }
}
