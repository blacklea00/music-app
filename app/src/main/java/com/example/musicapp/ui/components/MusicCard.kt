package com.example.musicapp.ui.components

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musicapp.R


@Composable
fun MusicCard(musicImage: Bitmap) {

    Row(
        Modifier
            .width(240.dp)
            .clickable {  },
        verticalAlignment = Alignment.Top
    ) {
        // music cover
        Column(
            Modifier
                .fillMaxWidth(0.4f)
        ) {
            Image(
                bitmap = musicImage.asImageBitmap(),
                contentDescription = "image",
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
            )
        }

        // singer and music name
        Column(
            Modifier
                .fillMaxWidth(0.6f)
        ) {
            Text(
                text = "Love Story",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(R.color.text_pink)
                )
            )
            Spacer(Modifier.padding(vertical = 2.dp))
            Text(
                text = "Taylor Swift",
                style = TextStyle(
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(R.color.text_black)
                )
            )
        }
    }
}