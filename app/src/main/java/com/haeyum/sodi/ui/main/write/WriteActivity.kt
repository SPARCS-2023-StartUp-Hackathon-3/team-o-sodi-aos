package com.haeyum.sodi.ui.main.write

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.haeyum.sodi.ui.main.write.ui.theme.SODITheme

class WriteActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SODITheme {
                val systemUiController = rememberSystemUiController()

                Column(modifier = Modifier.fillMaxSize()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Close,
                            contentDescription = "??????",
                            modifier = Modifier.clickable(onClick = ::finish),
                            tint = Color.Black
                        )

                        Text(
                            text = "??? ?????????",
                            color = Color.Black,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium,
                            letterSpacing = 2.sp
                        )

                        Text(
                            text = "??????",
                            modifier = Modifier.clickable(onClick = ::finish),
                            color = Color.Black,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium,
                            letterSpacing = 2.sp
                        )
                    }
                    Divider(thickness = (1.5).dp, color = Color(0xFFEFEFEF))

                    Divider(thickness = 1.dp, color = Color.Black)
                    RectangleButton(text = "??? ??????") {

                    }
                    RectangleButton(text = "?????? ??????") {

                    }
                    RectangleButton(text = "?????? ????????????") {

                    }
                    RectangleButton(text = "?????????") {

                    }
                }

                SideEffect {
                    systemUiController.setStatusBarColor(color = Color.White)
                }
            }
        }
    }

    @Composable
    private fun RectangleButton(text: String, onClick: () -> Unit) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { }
                .padding(12.dp),
        ) {
            Text(
                text = text,
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }
        Divider(thickness = 1.dp, color = Color.Black)
    }
}