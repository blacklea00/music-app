package com.example.musicapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.musicapp.R
import com.example.musicapp.data.model.NavItem

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    item: NavItem,
    barTextColor: TextStyle,
    isSelected: Boolean = false,
    onClick: () -> Unit,
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.clickable(
            onClick = onClick
        )
    ) {
        Icon(
            imageVector = if (isSelected) item.selectedIcon else item.unselectedIcon,
            tint = colorResource(R.color.text_pink),
            contentDescription = null
        )
        Text(stringResource(item.title), style = barTextColor.copy(
            fontWeight = FontWeight.Bold
        ))
    }
}