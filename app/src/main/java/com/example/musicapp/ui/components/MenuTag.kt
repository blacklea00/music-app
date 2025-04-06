package com.example.musicapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musicapp.R

@Composable
fun MenuTag(title: String) {
    Box(
        modifier = Modifier
            .background(
                color = colorResource(R.color.bar_color),
                shape = RoundedCornerShape(10.dp)
            )
            .wrapContentSize()
            .clickable {  }
    ) {
        Text(
            text = title,
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 8.dp),
            style = TextStyle(
                color = colorResource(R.color.text_pink),
                fontWeight = FontWeight.Bold,
                fontSize = 19.sp
            )
        )
    }
}