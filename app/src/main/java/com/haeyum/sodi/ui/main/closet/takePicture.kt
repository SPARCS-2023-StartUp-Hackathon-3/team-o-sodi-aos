package com.haeyum.sodi.ui.main.closet

import android.content.Intent
import android.graphics.Bitmap
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import com.haeyum.sodi.supports.LogHelper

fun takePicture(componentActivity: ComponentActivity, onReceivePicture: (Bitmap) -> Unit) {
    val launcher =
        componentActivity.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            onReceivePicture(it.data?.extras?.get("data") as Bitmap)
        }

    launcher.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
}