package com.example.musicapp.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Article
import androidx.compose.material.icons.automirrored.outlined.QueueMusic
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.outlined.Album
import androidx.compose.material.icons.outlined.Download
import androidx.compose.material.icons.outlined.NotInterested
import androidx.compose.material.icons.outlined.Radio
import androidx.compose.material.icons.outlined.ThumbDown
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.musicapp.R
import com.example.musicapp.constants.ImageConstants

@Composable
fun SongSetting() {
    Column(
        modifier = Modifier
            .padding(15.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        SongDetail()

        HorizontalDivider()

        Spacer(modifier = Modifier.height(10.dp))
        SongDetailBody()
        Spacer(modifier = Modifier.height(2.dp))
    }
}

@Composable
fun SongDetail() {
    Row(
        modifier = Modifier
            .height(70.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // song image
        Image(
            painter = rememberAsyncImagePainter(ImageConstants.IMAGE_2),
            contentDescription = "",
            modifier = Modifier
                .clip(shape = RoundedCornerShape(10.dp))
        )

        // title
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(top = 10.dp),
            verticalArrangement = Arrangement.spacedBy(3.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                "Song name", style = TextStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                "Singer", style = TextStyle(
                    fontSize = 13.sp,
                    color = colorResource(R.color.text_black)
                )
            )
        }

        // like button
        Row(
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

@Composable
fun SongDetailBody() {

    Column(
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        SongSetting("Add to queue", Icons.AutoMirrored.Outlined.QueueMusic)
        SongSetting("Download", Icons.Outlined.Download)
        SongSetting("Go to artist", Icons.AutoMirrored.Outlined.Article)
        SongSetting("Go to album", Icons.Outlined.Album)
        SongSetting("Start radio", Icons.Outlined.Radio)
        SongSetting("Not interested", Icons.Outlined.NotInterested)
    }
}

@Composable
fun SongSetting(name: String, imageVector: ImageVector) {

    Surface(
        onClick = {}
    ) {
        Row(
            Modifier
                .padding(10.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Icon(
                imageVector = imageVector,
                contentDescription = "",
            )
            Text(
                text = name,
                modifier = Modifier
                    .weight(1f)
            )
        }
    }
}