package com.haeyum.sodi.ui.main.discover

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Comment
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.haeyum.sodi.R
import com.haeyum.sodi.ui.main.productDetail.ProductDetailActivity

@Composable
fun DiscoverScreen(viewModel: DiscoverViewModel = hiltViewModel(), modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(Color.White)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Header()
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .background(Color(0xFFFCFCFC))
            ) {
                repeat(15) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .clickable {
                                viewModel.showPopup.value = true
                            }
                            .shadow(
                                elevation = 6.dp,
                                shape = RoundedCornerShape(12.dp),
                                spotColor = Color(0xFF8973D8)
                            )
                            .background(color = Color.White, shape = RoundedCornerShape(12.dp))
                            .border(
                                width = (1).dp,
                                color = Color(0xFFEFEFEF),
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(vertical = 18.dp)
                    ) {
                        val context = LocalContext.current

                        Column(modifier = Modifier.padding(horizontal = 18.dp)) {
                            WriterProfile()
                            Spacer(modifier = Modifier.size(18.dp))
                            Row(modifier = Modifier.fillMaxWidth()) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_launcher_background),
                                    contentDescription = null,
                                    modifier = Modifier.weight(1f),
                                    contentScale = ContentScale.FillWidth
                                )
                            }
                        }
                        EquipItems {
                            context.startActivity(
                                Intent(
                                    context,
                                    ProductDetailActivity::class.java
                                )
                            )
                        }
                        Column(modifier = Modifier.padding(horizontal = 18.dp)) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .offset(x = (-12).dp),
                                horizontalArrangement = Arrangement.spacedBy((0).dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    var isFavorite by remember { mutableStateOf(false) }

                                    IconButton(
                                        onClick = { isFavorite = !isFavorite },
                                        modifier = Modifier.padding(0.dp)
                                    ) {
                                        Icon(
                                            imageVector = if (isFavorite) Icons.Outlined.Favorite else Icons.Outlined.FavoriteBorder,
                                            contentDescription = "favorite"
                                        )
                                    }
                                    Text(
                                        text = "1.2K",
                                        modifier = Modifier.offset(x = (-6).dp),
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Medium
                                    )
                                }
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    IconButton(onClick = { /*TODO*/ }) {
                                        Icon(
                                            imageVector = Icons.Outlined.Comment,
                                            contentDescription = "comment"
                                        )
                                    }
                                    Text(
                                        text = "525",
                                        modifier = Modifier.offset(x = (-6).dp),
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Medium
                                    )
                                }
                            }
                            Description()
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun WriterProfile() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_black),
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .size(48.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(
                text = "PangMoo",
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Text(
                text = "2021.08.01",
                color = Color.DarkGray,
                fontSize = 12.sp,
                fontWeight = FontWeight.Light
            )
        }
    }
}

@Composable
private fun Header() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Discover",
            color = Color.Black,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            letterSpacing = 2.sp
        )
    }
    Divider(thickness = (1.5).dp, color = Color(0xFFEFEFEF))
}

@Composable
private fun Description() {
    Text(
        text = "죽음의 한기 속에서 해커톤을 하는 당신, 이런 당신을 위한 코디입니다. 따뜻한 기모가 당신을 지켜줍니다.",
        color = Color.Black,
        fontSize = 12.sp,
        fontWeight = FontWeight.Light,
        lineHeight = 18.sp
    )
}

@Composable
private fun EquipItems(onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(top = 18.dp, bottom = 0.dp)
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        repeat(8) {
            if (it == 0)
                Spacer(modifier = Modifier.size(8.dp))

            Button(
                onClick = onClick,
                modifier = Modifier.size(64.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0x88C2B7EF)),
                contentPadding = PaddingValues(0.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_black),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Fit
                )
            }

            if (it == 7)
                Spacer(modifier = Modifier.size(8.dp))
        }
    }
}