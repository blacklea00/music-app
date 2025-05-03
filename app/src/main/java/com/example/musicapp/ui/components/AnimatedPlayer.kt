package com.example.musicapp.ui.components

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

enum class PlayerState {
    COLLAPSED, EXPANDED
}

@Composable
fun AnimatedPlayer(state: PlayerState, onCollapse: () -> Unit, onExpand: () -> Unit) {
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
                    .clickable { onCollapse() }
            )

            // 播放器主体
            Box(
                modifier = Modifier
                    .offset(y = offsetY)
                    .fillMaxWidth()
                    .height(height)
                    .background(Color.DarkGray)
                    .align(Alignment.BottomCenter)
                    .clickable {
                        if (state == PlayerState.COLLAPSED) onExpand() else onCollapse()
                    }
            ) {
                Text("音乐播放器", color = Color.White, modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}


