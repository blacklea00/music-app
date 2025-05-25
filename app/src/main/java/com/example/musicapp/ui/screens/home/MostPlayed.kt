package com.example.musicapp.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import coil.compose.rememberAsyncImagePainter
import com.example.musicapp.R
import com.example.musicapp.constants.ImageConstants

@Composable
fun MostPlayed(onExpandPlayer: () -> Unit) {

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
                GridItem(content = item, musicImage, onExpandPlayer) // 每个格子内容
            }
        }

    }
}


@Composable
fun GridItem(content: String, imagePath: String, onExpandPlayer: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f) // 保持正方形
            .background(colorResource(R.color.white)) // 背景色
            .padding(1.dp)
            .clickable { onExpandPlayer() }
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