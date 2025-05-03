package com.example.musicapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbDown
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.outlined.ThumbDown
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.musicapp.R
import com.example.musicapp.constants.ImageConstants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalBottomSheet(showSheetState: Boolean) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showSheet by remember { mutableStateOf(showSheetState) }

    Column(

    ) {
//        Text("show bottom", Modifier.clickable { showSheet = true })
        Box(Modifier.padding(1.dp)) {
            Text("主界面内容")
        }

        if (showSheet) {
            ModalBottomSheet(
                onDismissRequest = { showSheet = false },
                modifier = Modifier
                    .fillMaxWidth(0.95f),
                sheetState = sheetState,
                shape = RoundedCornerShape(10.dp),
                dragHandle = null
            ) {
                Column (
                    modifier = Modifier
                        .padding(15.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {

                    SongDetail()

                    HorizontalDivider()

                    Text("这是一个 BottomSheet")
                    Button(onClick = { showSheet = false }) {
                        Text("关闭")
                    }
                }
            }
        }
    }
}

@Composable
fun SongDetail() {
    Row(
        modifier = Modifier
            .height(70.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(ImageConstants.IMAGE_2),
            contentDescription = "",
            modifier = Modifier
                .clip(shape = RoundedCornerShape(10.dp))
        )
        Column (
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(top = 10.dp),
            verticalArrangement = Arrangement.spacedBy(3.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text("Song name", style = TextStyle(
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            ))
            Text("Singer", style = TextStyle(
                fontSize = 13.sp,
                color = colorResource(R.color.text_black)
            ))
        }
        Row (
            modifier = Modifier
                .weight(0.5f)
                .fillMaxHeight(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.ThumbDown,
                contentDescription = "",
                modifier = Modifier.size(30.dp),
                tint = colorResource(R.color.text_pink)
            )
            Icon(
                imageVector = Icons.Filled.ThumbUp,
                contentDescription = "",
                modifier = Modifier.size(30.dp),
                tint = colorResource(R.color.text_pink)

            )
        }
    }
}