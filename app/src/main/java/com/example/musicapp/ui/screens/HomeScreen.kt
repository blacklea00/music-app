package com.example.musicapp.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.musicapp.R
import com.example.musicapp.constants.ImageConstants
import com.example.musicapp.ui.components.MenuTag
import com.example.musicapp.ui.components.SongSetting
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier, homeScrollState: ScrollState, onExpandPlayer: () -> Unit) {

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

        Text("click me", modifier = Modifier.clickable { onExpandPlayer() })

        // quick picks listing
        QuickPicks(musicBgList)

        // most played
        MostPlayed()

        // special dial
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
    Text(
        text = stringResource(R.string.title_1),
        style = TextStyle(
            color = colorResource(R.color.text_black),
            fontSize = 23.sp,
            fontWeight = FontWeight.Bold
        )
    )

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

    Text(
        text = stringResource(R.string.title_2),
        style = TextStyle(
            color = colorResource(R.color.text_black),
            fontSize = 23.sp,
            fontWeight = FontWeight.Bold
        )
    )

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

// 方案一：优化的 LazyRow + Snapper
@OptIn(ExperimentalSnapperApi::class)
@Composable
fun SpecialDial(onClickHandleMoreSetting: () -> Unit) {
    val listState = rememberLazyListState()

    // 优化的 flingBehavior，去掉复杂的 snapIndex 逻辑
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

    Text(
        text = stringResource(R.string.title_3),
        style = TextStyle(
            color = colorResource(R.color.text_black),
            fontSize = 23.sp,
            fontWeight = FontWeight.Bold,
        ),
    )

    LazyRow(
        state = listState,
        flingBehavior = flingBehavior,
        contentPadding = PaddingValues(horizontal = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(15.dp), // 增加间距提升视觉效果
        modifier = Modifier.fillMaxWidth()
    ) {
        items(4) { index ->
            SpecialDialPage(
                pageIndex = index,
                onClickHandleMoreSetting = onClickHandleMoreSetting
            )
        }
    }

    Spacer(modifier = Modifier.height(10.dp))
}


@Composable
fun SpecialDialPage(
    pageIndex: Int,
    onClickHandleMoreSetting: () -> Unit
) {
    // 使用 Column 替代 LazyColumn，避免嵌套滚动冲突
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.width(330.dp)
    ) {
        repeat(4) { itemIndex ->
            SpecialDialContentBox(
                imagePath = ImageConstants.IMAGE_1,
                songTitle = "歌曲名 ${pageIndex * 4 + itemIndex + 1}", // 动态标题
                artist = "周杰伦",
                views = "${(100 + itemIndex * 50)}M views",
                onClickHandleMoreSetting = onClickHandleMoreSetting
            )
        }
    }
}

@Composable
fun SpecialDialContentBox(
    imagePath: String,
    songTitle: String,
    artist: String,
    views: String,
    onClickHandleMoreSetting: () -> Unit
) {
    Surface(
        onClick = { },
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        shape = RoundedCornerShape(10.dp),
        color = colorResource(R.color.white),
        shadowElevation = 2.dp // 添加轻微阴影
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 图片部分
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(RoundedCornerShape(8.dp))
            ) {
                Image(
                    painter = rememberAsyncImagePainter(imagePath),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            // 文字信息部分
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = songTitle,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = colorResource(R.color.text_black)
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "$artist · $views",
                    style = TextStyle(
                        fontSize = 13.sp,
                        color = colorResource(R.color.text_black).copy(alpha = 0.7f)
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            // 更多按钮 - 简化版本，不使用 ripple
            IconButton(
                onClick = { onClickHandleMoreSetting() },
                modifier = Modifier.size(40.dp)
            ) {
                Icon(
                    Icons.Default.MoreVert,
                    contentDescription = "更多选项",
                    tint = colorResource(R.color.text_black).copy(alpha = 0.6f)
                )
            }
        }
    }
}
