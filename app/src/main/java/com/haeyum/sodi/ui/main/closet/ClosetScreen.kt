package com.haeyum.sodi.ui.main.closet

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.haeyum.sodi.R
import com.haeyum.sodi.supports.LogHelper
import com.haeyum.sodi.ui.main.MainViewModel
import com.haeyum.sodi.ui.main.component.MainComponent
import kotlinx.coroutines.flow.collectLatest
import okhttp3.internal.toHexString
import kotlin.random.Random
import kotlin.random.nextInt
import kotlin.random.nextUInt

@Composable
fun ClosetScreen(
    viewModel: ClosetViewModel = hiltViewModel(),
    activityViewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    var bitmap by remember {
        mutableStateOf<Bitmap?>(null)
    }
    val colors = remember {
        hello()
    }

    Box(
        modifier = modifier
            .background(Color.White)
            .statusBarsPadding()
            .background(Color(0xFFFCFCFC))
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            MainComponent.Header(title = "내 옷장")
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TabRow(
                    selectedTabIndex = 0,
                    contentColor = Color(0xFF8973D8),
                    indicator = {
                        TabRowDefaults.Indicator(
                            Modifier.tabIndicatorOffset(it[viewModel.selectedTabIndex.value]),
                            color = Color(0xFF8973D8)
                        )
                    }
                ) {
                    Tab(
                        selected = false,
                        onClick = { viewModel.selectedTabIndex.value = 0 },
                    ) {
                        Text(
                            text = "전체",
                            modifier = Modifier.padding(12.dp),
                            color = Color(0xFF8973D8)
                        )
                    }
                    Tab(
                        selected = false,
                        onClick = { viewModel.selectedTabIndex.value = 1 },
                    ) {
                        Text(text = "구매한 옷", modifier = Modifier.padding(12.dp))
                    }
                    Tab(
                        selected = false,
                        onClick = { viewModel.selectedTabIndex.value = 2 },
                    ) {
                        Text(text = "등록한 옷", modifier = Modifier.padding(12.dp))
                    }
                }

                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    modifier = Modifier.background(Color(0xFFFCFCFC)),
                    contentPadding = PaddingValues(8.dp),
                    content = {
                        items(9) {
                            if (bitmap != null)
                                ClosetItem(bitmap!!)
                        }
                    },
                )
            }
        }
        FloatingActionButton(
            onClick = {
                activityViewModel.takePicture()
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(24.dp)
        ) {
            Icon(imageVector = Icons.Default.AddAPhoto, contentDescription = null)
        }

//        Box(
//            modifier = Modifier
//                .align(Alignment.Center)
//                .size(300.dp)
//                .background(brush = Brush.sweepGradient(colors)),
//            contentAlignment = Alignment.Center
//        ) {
//            Image(
//                painter = painterResource(id = R.drawable.ic_launcher_background),
//                contentDescription = null
//            )
//        }
    }

    LaunchedEffect(Unit) {
        activityViewModel.takePictureBitmap.collectLatest {
            bitmap = it
        }
    }
}

fun hello(): List<Color> {
    val colors = arrayListOf<Color>()
    repeat(Random.nextInt(8, 24)) {
        val r = Random.nextFloat()
        val g = Random.nextFloat()
        val b = Random.nextFloat()
        colors.add(Color(r, g, b))
    }

    return colors
}

@Composable
private fun ClosetItem(bitmap: Bitmap) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .border(width = 1.dp, color = Color(0xFF000000)),
    ) {
//        Image(
//            painter = painterResource(id = R.drawable.ic_launcher_background),
//            contentDescription = null,
//            modifier = Modifier.fillMaxSize(),
//            contentScale = ContentScale.FillBounds
//        )

        Image(
            bitmap = bitmap.asImageBitmap(),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f),
            contentScale = ContentScale.FillBounds
        )

    }
}