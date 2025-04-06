package com.example.musicapp.ui.screens

import android.content.Context
import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musicapp.R
import com.example.musicapp.ui.components.MenuTag
import com.example.musicapp.ui.components.MusicCard
import com.example.musicapp.util.loadBitmapFromAssets


@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

    val homeScrollState = rememberScrollState()
    val menuScrollState = rememberScrollState()
    val recommendScrollState = rememberScrollState()


    val context = LocalContext.current

    val menuList = listOf(
        stringResource(R.string.menu_recommend),
        stringResource(R.string.menu_popular),
        stringResource(R.string.menu_like),
        stringResource(R.string.menu_collect)
    )

    val spacerVertical = Modifier.padding(vertical = 15.dp)


    // for load image
    val musicBg1 = remember { context.loadBitmapFromAssets("music_bg_1.jpg") }
    val musicBg2 = remember { context.loadBitmapFromAssets("music_bg_2.jpg") }
    val musicBg3 = remember { context.loadBitmapFromAssets("music_bg_3.jpg") }

    val musicBgList = listOf(
        musicBg1,
        musicBg2,
        musicBg3
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
            .verticalScroll(homeScrollState)
    ) {
        // display top menu
        Row(modifier = Modifier.horizontalScroll(menuScrollState)) {

            menuList.forEach { menu ->
                MenuTag(menu)
                Spacer(modifier = Modifier.padding(horizontal = 5.dp))
            }
        }

        Spacer(modifier = spacerVertical)

        // quick picks 字体
        Text(
            text = stringResource(R.string.title_1),
            style = TextStyle(
                color = colorResource(R.color.text_black),
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold
            )
        )

        Spacer(modifier = Modifier.padding(vertical = 15.dp))

        Row (
            modifier = Modifier
                .horizontalScroll(recommendScrollState)
        ) {

            MusicRecommend(musicBgList)

            Spacer(modifier = Modifier.padding(horizontal = 10.dp))

            MusicRecommend(musicBgList)

            Spacer(modifier = Modifier.padding(horizontal = 10.dp))

            MusicRecommend(musicBgList)

        }
    }
}


@Composable
fun MusicRecommend(musicBgList: List<Bitmap>) {
    Box {
        // music list box
        Column(
            Modifier
                .clip(shape = RoundedCornerShape(6.dp).copy(topEnd = CornerSize(50.dp)))
                .background(
                    // card background color
                    color = colorResource(R.color.music_card_pink)
                )
        ) {

            Spacer(modifier = Modifier.padding(5.dp))

            // title
            Text(
                text = "2025 hots",
                modifier = Modifier
                    .padding(horizontal = 10.dp),
                style = TextStyle(
                    fontSize = 20.sp,
                    color = colorResource(R.color.black),
                    fontWeight = FontWeight.Bold
                )
            )

            Spacer(modifier = Modifier.padding(3.dp))

            // load music card
            musicBgList.forEach{ item ->
                Spacer(modifier = Modifier.padding(5.dp))

                MusicCard(item)
            }

            Spacer(modifier = Modifier.padding(5.dp))

        }
    }
}