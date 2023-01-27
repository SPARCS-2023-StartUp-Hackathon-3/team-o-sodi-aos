//package com.haeyum.sodi.ui.component
//
//import androidx.compose.foundation.layout.PaddingValues
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.Button
//import androidx.compose.material3.ButtonDefaults
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//
//@Composable
//fun RoundedButton(text: String, onClick: () -> Unit) {
//    Button(
//        onClick = onClick,
//        modifier = Modifier
//            .padding(top = 36.dp)
//            .fillMaxWidth(),
//        shape = RoundedCornerShape(16.dp),
//        colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
//        contentPadding = PaddingValues(16.dp)
//    ) {
//        Text(
//            text = text,
//            color = Color.White,
//            fontSize = 16.sp,
//            fontWeight = FontWeight.Light
//        )
//    }
//}