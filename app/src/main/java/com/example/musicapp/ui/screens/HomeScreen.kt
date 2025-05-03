package com.example.musicapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.trace
import coil.compose.rememberAsyncImagePainter
import coil.request.Tags
import com.example.musicapp.R
import com.example.musicapp.constants.ImageConstants
import com.example.musicapp.ui.components.AnimatedPlayer
import com.example.musicapp.ui.components.MenuTag
import com.example.musicapp.ui.components.ModalBottomSheet
import com.example.musicapp.ui.components.PlayerState
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior

@Composable
fun HomeScreen(modifier: Modifier = Modifier, homeScrollState: ScrollState, onExpandPlayer: () -> Unit) {


    val musicBgList = listOf(
        ImageConstants.IMAGE_1,
        ImageConstants.IMAGE_2,
        ImageConstants.IMAGE_3
    )

    var showSheet by remember { mutableStateOf(false) }
    ModalBottomSheet(showSheet)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
            .verticalScroll(homeScrollState),
        verticalArrangement = Arrangement.spacedBy(25.dp)
    ) {
        // display top tags
        Tags()


        Text("click me", modifier = Modifier.clickable { onExpandPlayer() })

        // quick picks word
        Text(
            text = stringResource(R.string.title_1),
            style = TextStyle(
                color = colorResource(R.color.text_black),
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold
            )
        )

        // quick picks listing
        QuickPicks(musicBgList)

        // most played

        Text(
            text = stringResource(R.string.title_2),
            style = TextStyle(
                color = colorResource(R.color.text_black),
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold
            )
        )


        MostPlayed()

        // special dial

        Text(
            text = stringResource(R.string.title_3),
            style = TextStyle(
                color = colorResource(R.color.text_black),
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold,
            ),
        )

        SpecialDial(onClickHandleMoreSetting = { showSheet = true })

    }
}

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

@Composable
fun QuickPicks(musicBgList: List<String>) {
    LazyRow (
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(10.dp)
    ) {
        items(musicBgList, key = { it }) { imagePath ->
            Surface(
                onClick = {},
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .width(250.dp)
                    .height(250.dp)
            ) {
                Column (
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(imagePath),
                        contentDescription = "",
                        Modifier.fillMaxWidth()
                    )
                    Column (
                        modifier = Modifier
                            .padding(start = 10.dp)
                    ) {
                        Text(
                            text = "Fantasy baby",
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 17.sp,
                                color = colorResource(R.color.text_black)
                            )
                        )
                        Text(
                            text = "BigBang - 261M views",
                            style = TextStyle(
                                fontSize = 13.sp,
                                color = colorResource(R.color.text_black)
                            )
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun MostPlayed() {

    val items = listOf("稻香", "我的滑板鞋", "搁浅", "爱错", "偏爱", "我爱你，但是我要回家", "盛夏光年", "慢冷", "普通朋友")
    val musicImage = ImageConstants.IMAGE_2

    Box (modifier = Modifier.height(370.dp)) {

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
}

@Composable
fun GridItem(content: String, imagePath: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f) // 保持正方形
            .background(colorResource(R.color.white)) // 背景色
            .padding(1.dp)
            .clickable { }
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
                    shape = RoundedCornerShape(10.dp).copy(
                        topEnd = CornerSize(0.dp),
                        topStart = CornerSize(0.dp)
                    )
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

@OptIn(ExperimentalSnapperApi::class)
@Composable
fun SpecialDial(onClickHandleMoreSetting: () -> Unit) {

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

    LazyRow (
        state = listState,
        flingBehavior = flingBehavior,
        contentPadding = PaddingValues(horizontal = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(4, key = {it}) { index ->

            Column (
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.width(330.dp)
            ) {
                repeat(4) {
                    SpecialDialContentBox(ImageConstants.IMAGE_1, onClickHandleMoreSetting)
                }
            }

        }
    }

}

@Composable
fun SpecialDialContentBox(imagePath: String, onClickHandleMoreSetting: () -> Unit) {
    Box(
        modifier = Modifier
            .clickable { }
            .height(60.dp)
            .wrapContentWidth()
            .background(color = colorResource(R.color.white), shape = RoundedCornerShape(10.dp))
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
                    .padding(2.dp)
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
                    .clickable { onClickHandleMoreSetting() }
            ) {
                Icon(
                    Icons.Default.MoreVert,
                    contentDescription = "",
                )
            }
        }
    }
}