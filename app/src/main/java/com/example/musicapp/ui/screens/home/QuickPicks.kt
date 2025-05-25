package com.example.musicapp.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.musicapp.R

@Composable
fun QuickPicks(musicBgList: List<String>, onExpandPlayer: () -> Unit) {
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
                onClick = {onExpandPlayer()},
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
