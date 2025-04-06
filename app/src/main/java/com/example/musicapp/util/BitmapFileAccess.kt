package com.example.musicapp.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory

fun Context.loadBitmapFromAssets(fileName: String): Bitmap {
    return assets.open(fileName).use { stream ->
        BitmapFactory.decodeStream(stream)
    }
}