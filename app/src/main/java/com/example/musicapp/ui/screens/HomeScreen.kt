package com.example.musicapp.ui.screens

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.musicapp.R
import com.example.musicapp.ui.components.MenuTag
import com.example.musicapp.ui.components.MusicCard
import com.example.musicapp.util.loadBitmapFromAssets
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior

@OptIn(ExperimentalSnapperApi::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

    val homeScrollState = rememberScrollState()
    val menuScrollState = rememberScrollState()
    val recommendScrollState = rememberScrollState()


    val listState = rememberLazyListState()
    val flingBehavior = rememberSnapperFlingBehavior(
        lazyListState = listState,
        snapIndex = { layoutInfo, startIndex, targetIndex ->
            val maxStep = 1
            when {
                targetIndex > startIndex -> (startIndex + maxStep).coerceAtMost(layoutInfo.totalItemsCount - 1)
                targetIndex < startIndex -> (startIndex - maxStep).coerceAtLeast(0)
                else -> targetIndex
            }
        }
    )


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

        // quick picks word
        Text(
            text = stringResource(R.string.title_1),
            style = TextStyle(
                color = colorResource(R.color.text_black),
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold
            )
        )

        Spacer(modifier = Modifier.padding(vertical = 15.dp))

        // quick picks listing
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

        // most played
        Spacer(modifier = Modifier.padding(vertical = 15.dp))

        Text(
            text = stringResource(R.string.title_2),
            style = TextStyle(
                color = colorResource(R.color.text_black),
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold
            )
        )

        Spacer(modifier = Modifier.padding(vertical = 15.dp))

        Box (modifier = Modifier.height(370.dp)) {
            MostPlayed()
        }

        Spacer(modifier = Modifier.padding(vertical = 15.dp))


        // special dial

        Text(
            text = stringResource(R.string.title_3),
            style = TextStyle(
                color = colorResource(R.color.text_black),
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold
            )
        )

        Spacer(modifier = Modifier.padding(vertical = 15.dp))


        LazyRow (
            state = listState,
            flingBehavior = flingBehavior,
            contentPadding = PaddingValues(horizontal = 16.dp),
//            modifier = Modifier
//                .horizontalScroll(specialDialScrollState),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(4) { index ->
                SpecialDial() // 你的 item 组件
            }

        }

        Spacer(modifier = Modifier.padding(vertical = 12.dp))

    }
}


@Composable
fun MusicRecommend(musicBgList: List<Bitmap>) {
    Box {
        // music list box
        Column(
            Modifier
                .clip(shape = RoundedCornerShape(6.dp).copy(topEnd = CornerSize(50.dp)))
                .border(
                    width = 2.dp,
                    color = colorResource(R.color.text_pink),
                    shape = RoundedCornerShape(6.dp).copy(topEnd = CornerSize(50.dp))
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
                    color = colorResource(R.color.text_pink),
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


@Composable
fun MostPlayed() {
    val context = LocalContext.current

    val items = listOf("稻香", "我的滑板鞋", "搁浅", "爱错", "偏爱", "我爱你，但是我要回家", "盛夏光年", "慢冷", "普通朋友")
    val musicImage = "file:///android_asset/music_bg_1.jpg"

    LazyVerticalGrid(
        columns = GridCells.Fixed(3), // 3列固定
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(2.dp) // 内边距
    ) {
        items(items) { item ->
            GridItem(content = item, musicImage) // 每个格子内容
        }
    }
}

@Composable
fun GridItem(content: String, imagePath: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f) // 保持正方形
            .background(colorResource(R.color.white)) // 背景色
            .padding(1.dp)
            .clickable {  }
        ,
    ) {
        // 图片（占满整个 Box）
        Image(
            painter = rememberAsyncImagePainter(imagePath),
            contentDescription = "image",
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Crop // 裁剪图片以适应区域
        )

        // 文字（固定在底部中间）
        Box (
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,         // 顶部透明
                            Color.Black.copy(alpha = 0.8f), // 中间半透明
                            Color.Black.copy(alpha = 0.9f)  // 底部不透明
                        ),
                        startY = 0f,  // 从顶部开始
                        endY = Float.POSITIVE_INFINITY // 到底部结束
                    ),
                    shape = RoundedCornerShape(10.dp).copy(topEnd = CornerSize(0.dp), topStart = CornerSize(0.dp))
                )
                .align(Alignment.BottomStart) // 关键：定位到底部中心
                .padding(start = 8.dp, bottom = 4.dp) // 与底部的距离
        ) {
            Text(
                text = content,
                style = TextStyle(
                    color = colorResource(R.color.white),
                    textAlign = TextAlign.Center // 文字内容居中
                ),
            )
        }
    }
}

@Composable
fun SpecialDial() {

    Column (
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.width(330.dp)
    ) {
        repeat(4) {
            SpecialDialContentBox("file:///android_asset/music_bg_1.jpg")
        }
    }

}

@Composable
fun SpecialDialContentBox(imagePath: String) {
    Box(
        modifier = Modifier
            .clickable {  }
            .height(60.dp)
            .wrapContentWidth()
    ) {
        Row (
            modifier = Modifier
                .fillMaxHeight(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box (
                modifier = Modifier
                    .weight(1f)
                    .clip(shape = RoundedCornerShape(10.dp))
            ) {
                Image(
                    painter = rememberAsyncImagePainter(imagePath),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize(),
                )
            }
            Box(
                modifier = Modifier
                    .weight(3f)
                    .fillMaxHeight()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        "歌曲名",
                        style = TextStyle(
                            fontWeight = FontWeight.ExtraBold
                        )
                    )
                    Text(
                        "周杰伦 100M views",
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = colorResource(R.color.text_black)
                        )
                    )
                }
            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clickable {  }
            ) {
                Icon(
                    Icons.Default.MoreVert,
                    contentDescription = "",
                )
            }
        }
    }
}