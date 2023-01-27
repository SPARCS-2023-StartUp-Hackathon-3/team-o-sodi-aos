package com.haeyum.sodi.ui.intro.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object IntroComponent {
    @Composable
    fun RoundedButton(
        text: String,
        modifier: Modifier = Modifier,
        enabled: Boolean = true,
        onClick: () -> Unit
    ) {
        Button(
            onClick = onClick,
            modifier = modifier,
            enabled = enabled,
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                disabledContainerColor = Color.LightGray
            ),
            contentPadding = PaddingValues(16.dp)
        ) {
            Text(
                text = text,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Light
            )
        }
    }

    @Composable
    fun CardTextField(
        type: String,
        hint: String,
        value: String,
        onValueChange: (String) -> Unit,
        visualTransformation: VisualTransformation = VisualTransformation.None
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(12.dp),
                    spotColor = Color(0xFF111111)
                )
                .background(color = Color.White, shape = RoundedCornerShape(12.dp))
                .padding(16.dp),
        ) {
            Text(text = type, color = Color.Black, fontWeight = FontWeight.Medium)
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier
                    .padding(top = 6.dp)
                    .fillMaxWidth(),
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light
                ),
                singleLine = true,
                visualTransformation = visualTransformation
            ) {
                if (value.isEmpty())
                    Text(
                        text = hint,
                        color = Color.Gray,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Light
                    )

                it()
            }
        }
    }

    @Composable
    fun NavigateTextButton(text: String, highlightText: String? = null, onClick: () -> Unit) {
        TextButton(onClick = onClick) {
            Text(
                text = buildAnnotatedString {
                    append(text)

                    if (highlightText != null)
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Medium)) {
                            append(highlightText)
                        }
                },
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal
            )
        }
    }
}
