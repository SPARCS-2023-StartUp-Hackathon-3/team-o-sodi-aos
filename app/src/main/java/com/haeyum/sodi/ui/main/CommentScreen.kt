package com.haeyum.sodi.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import com.haeyum.sodi.R

@Composable
fun CommentScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        Row {
            Image(
                painter = painterResource(id = R.drawable.logo_black),
                contentDescription = null,
                modifier = Modifier.clip(CircleShape)
            )
        }
    }
}