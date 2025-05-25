package com.example.musicapp.ui.screens.home

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material.icons.outlined.PauseCircleOutline
import androidx.compose.material.icons.outlined.PlayCircle
import androidx.compose.material.icons.outlined.Repeat
import androidx.compose.material.icons.outlined.Shuffle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.rememberAsyncImagePainter
import com.example.musicapp.constants.ImageConstants

enum class PlayerState {
    COLLAPSED, EXPANDED
}

@Composable
fun MusicPlayer(state: PlayerState, onCollapse: () -> Unit, onExpand: () -> Unit) {
    val transition = updateTransition(targetState = state, label = "player_transition")

    val height = 1000.dp

    val offsetY by transition.animateDp(label = "offsetY") { targetState ->
        if (targetState == PlayerState.EXPANDED) 0.dp else height
    }

    val backgroundAlpha by transition.animateFloat(label = "bgAlpha") { targetState ->
        if (targetState == PlayerState.EXPANDED) 0.6f else 0f
    }

    // 仅当有需要时显示播放器组件
    if (state != PlayerState.COLLAPSED || backgroundAlpha > 0f) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .zIndex(1f)  // 确保显示在最顶层
        ) {
            // 半透明背景遮罩
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = backgroundAlpha))
//                    .clickable { onCollapse() }
            ) {
            }

            Player(Modifier.offset(y = offsetY), onCollapse)
        }
    }
}

@Composable
fun Player(modifier: Modifier = Modifier, onCollapse: () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xff710094),
                        Color(0xff4f0094),
                        Color(0xff2711d1),
                        Color(0xff1171d1),
                        Color(0xff9ecfff)
                    )
                )
            )
    ) {
        Column (
//            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Spacer(Modifier.size(35.dp))

            TopControl(onCollapse = onCollapse)

            Spacer(Modifier.size(60.dp))


            Box(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(12.dp))
            ) {
                // 背景图片
                Image(
                    painter = rememberAsyncImagePainter(ImageConstants.IMAGE_4),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                // 模拟玻璃效果的遮罩层
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.White.copy(alpha = 0.3f),
                                    Color.White.copy(alpha = 0.05f)
                                )
                            )
                        )
                        .border(
                            width = 1.dp,
                            color = Color.White.copy(alpha = 0.3f),
                            shape = RoundedCornerShape(12.dp)
                        )
                )
            }

            Spacer(Modifier.size(20.dp))

            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
            ) {
                Text(
                    text = "兰亭序",
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        color = Color.White,
                        textAlign = TextAlign.Start
                    ),
                )
                Text(
                    text = "周杰伦",
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.titleSmall.fontSize,
                        fontWeight = FontWeight.Bold,
                        color = Color.LightGray.copy(alpha = 1f),
                        textAlign = TextAlign.Start,
                    ),
                )

            }

            Spacer(modifier = Modifier.size(30.dp))
            PlayButton(modifier = Modifier
                .padding(bottom = 30.dp))
        }

    }

}

@Composable
fun PlayButton(modifier: Modifier = Modifier) {

    var progress by remember { mutableStateOf(0f) }
    var isPlayed by remember { mutableStateOf(false) }

    Column (
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
    ) {

        MusicSlider(
            progress = progress,
            onProgressChanged = { progress = it }
        )

        Spacer(modifier = Modifier.size(10.dp))

        Row (
            horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth(0.8f)
        ) {

            Icon(
                imageVector = Icons.Outlined.Shuffle,
                contentDescription = Icons.Outlined.Shuffle.name,
                tint = Color.White,
                modifier = Modifier.size(25.dp)
            )

            Icon(
                imageVector = Icons.Filled.SkipPrevious,
                contentDescription = Icons.Filled.SkipPrevious.name,
                tint = Color.White,
                modifier = Modifier.size(40.dp)
            )

            IconButton(
                onClick = {isPlayed = !isPlayed},
                modifier = Modifier.size(70.dp)
            ) {
                Icon(
                    imageVector = if (isPlayed) Icons.Outlined.PlayCircle else Icons.Outlined.PauseCircleOutline,
                    contentDescription = if (isPlayed) Icons.Outlined.PlayCircle.name else Icons.Outlined.PauseCircleOutline.name,
                    tint = Color.White,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Icon(
                imageVector = Icons.Filled.SkipNext,
                contentDescription = Icons.Filled.SkipNext.name,
                tint = Color.White,
                modifier = Modifier.size(40.dp)
            )

            Icon(
                imageVector = Icons.Outlined.Repeat,
                contentDescription = Icons.Outlined.Repeat.name,
                tint = Color.White,
                modifier = Modifier.size(25.dp)
            )
        }

        Spacer(modifier = Modifier.size(80.dp))

    }

}

@Composable
fun MusicSlider(
    progress: Float, // 当前进度，范围是 0f 到 1f
    onProgressChanged: (Float) -> Unit
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth(0.9f)
    ) {
        Slider(
            value = progress,
            onValueChange = onProgressChanged,
            modifier = Modifier
                .fillMaxWidth(),
            colors = SliderDefaults.colors(
                thumbColor = Color.White,
                activeTrackColor = Color.White
            )
        )
        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 7.dp)
        ) {
            Text(text = "0:00", color = Color.White)
            Text(text = "3:45", color = Color.White)
        }
    }
}

@Composable
fun TopControl(modifier: Modifier = Modifier, onCollapse: () -> Unit) {
    Box(
        modifier = modifier
            .fillMaxWidth(0.9f)
    ) {

        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton (
                onClick = { onCollapse() }
            ) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowDown,
                    contentDescription = Icons.Filled.KeyboardArrowDown.name,
                    tint = Color.White,
                    modifier = Modifier.size(25.dp)
                )
            }
            IconButton (
                onClick = { }
            ) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = Icons.Filled.MoreVert.name,
                    tint = Color.White,
                    modifier = Modifier.size(25.dp)
                )
            }
        }
    }

}