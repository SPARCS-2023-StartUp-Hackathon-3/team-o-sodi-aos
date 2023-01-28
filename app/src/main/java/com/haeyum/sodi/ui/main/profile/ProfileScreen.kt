package com.haeyum.sodi.ui.main.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Album
import androidx.compose.material.icons.outlined.Block
import androidx.compose.material.icons.outlined.BorderOuter
import androidx.compose.material.icons.outlined.BrowseGallery
import androidx.compose.material.icons.outlined.DoorFront
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.GridView
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.haeyum.sodi.R
import com.haeyum.sodi.ui.main.component.MainComponent

@Composable
fun ProfileScreen(viewModel: ProfileViewModel = hiltViewModel(), modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .background(Color.White)
            .statusBarsPadding()
    ) {
        MainComponent.Header(title = "Profile")
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(28.dp))
            Image(
                painter = painterResource(id = R.drawable.logo_black),
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(96.dp)
            )

            Spacer(modifier = Modifier.size(20.dp))
            Text(
                text = "Kawai PangMoo",
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.size(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(0.75f),
                horizontalArrangement = Arrangement.Center
            ) {
                CountText(count = 24, name = "posts", modifier = Modifier.weight(1f))
                CountText(count = 987, name = "follower", modifier = Modifier.weight(1f))
                CountText(count = 120, name = "following", modifier = Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.size(24.dp))

            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(100.dp))
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color(0xFFC2B7EF),
                                Color(0xFF8973D8)
                            )
                        )
                    )
                    .fillMaxWidth(0.45f)
                    .aspectRatio(4f)
                    .clickable {

                    },
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Follow",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.size(36.dp))
        }

        Divider(color = Color(0xFFEFEFEF), thickness = (1.5).dp)
        TabRow(selectedTabIndex = 0) {
            Tab(selected = true, onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Outlined.GridView,
                    contentDescription = "Posts",
                    modifier = Modifier.padding(12.dp)
                )
            }
            Tab(selected = false, onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Outlined.FavoriteBorder,
                    contentDescription = "Favorites",
                    modifier = Modifier.padding(12.dp)
                )
            }
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .weight(1f)
                .background(Color(0xFFEFEFEF))
                .padding(horizontal = 8.dp),
            content = {
                items(99) {
                    Image(
                        painter = painterResource(id = R.drawable.logo_black),
                        contentDescription = null,
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .padding(top = 8.dp)
                            .then(
                                when {
                                    it % 3 == 0 -> Modifier.padding(end = 4.dp)
                                    it % 3 == 1 -> Modifier.padding(horizontal = 4.dp)
                                    else -> Modifier.padding(start = 4.dp)
                                }
                            )
                            .size(120.dp)
//                            .weight(1f)
                    )
                }
                item {
                    Spacer(modifier = Modifier.size(8.dp))
                }
            },
        )
    }
}

@Composable
private fun CountText(count: Int, name: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = count.toString(),
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(
            text = name,
            color = Color(0xFF888888),
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center
        )
    }
}