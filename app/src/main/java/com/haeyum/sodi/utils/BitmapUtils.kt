package com.haeyum.sodi.utils

import android.graphics.Bitmap

object BitmapUtils {
    fun resizeBitmap(bitmap: Bitmap, width: Int, height: Int): Bitmap {
        return Bitmap.createScaledBitmap(bitmap, width, height, true)
    }
}