package com.example.musicapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MusicOff
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musicapp.R

@Composable
fun TopBar() {
    Box (
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(25.dp)
            )
            Row (
                verticalAlignment = Alignment.Bottom
            ) {
                // Play Icon
                Box(
                    modifier = Modifier
                        .background(
                            color = colorResource(R.color.text_pink),
                            shape = RoundedCornerShape(50.dp)
                        )
                ) {
                    Icon(
                        imageVector = Icons.Filled.MusicOff,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(5.dp),
                        tint = colorResource(R.color.white)
                    )
                }

                Spacer(modifier = Modifier.width(5.dp))

                // Title
                Text(
                    text = stringResource(R.string.app_name),
                    style = TextStyle(
                        color = colorResource(R.color.text_pink),
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    )
                )

                // Notification and Search icon
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd // 靠右对齐
                ) {
                    Row {
                        Icon(
                            imageVector = Icons.Outlined.Notifications,
                            contentDescription = null,
                            modifier = Modifier.size(35.dp),
                            tint = colorResource(R.color.text_pink)
                        )

                        Spacer(modifier = Modifier.width(20.dp))

                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null,
                            modifier = Modifier.size(35.dp),
                            tint = colorResource(R.color.text_pink)
                        )
                    }
                }


            }
        }
    }
}