package com.example.musicapp.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.musicapp.R
import com.example.musicapp.constants.ImageConstants
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior

@OptIn(ExperimentalSnapperApi::class)
@Composable
fun SpecialDial(onClickHandleMoreSetting: () -> Unit, onExpandPlayer: () -> Unit) {
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
                onClickHandleMoreSetting = onClickHandleMoreSetting,
                onExpandPlayer
            )
        }
    }

    Spacer(modifier = Modifier.height(10.dp))
}


@Composable
fun SpecialDialPage(
    pageIndex: Int,
    onClickHandleMoreSetting: () -> Unit,
    onExpandPlayer: () -> Unit
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
                onClickHandleMoreSetting = onClickHandleMoreSetting,
                onExpandPlayer
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
    onClickHandleMoreSetting: () -> Unit,
    onExpandPlayer: () -> Unit
) {
    Surface(
        onClick = { onExpandPlayer() },
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
