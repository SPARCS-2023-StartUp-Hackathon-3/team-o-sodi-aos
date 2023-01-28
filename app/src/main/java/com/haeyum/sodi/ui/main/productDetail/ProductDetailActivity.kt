package com.haeyum.sodi.ui.main.productDetail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import coil.compose.AsyncImage
import com.haeyum.sodi.R
import com.haeyum.sodi.ui.theme.SODITheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterialApi::class)
@AndroidEntryPoint
class ProductDetailActivity : ComponentActivity() {
    val viewModel by viewModels<ProductDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = android.graphics.Color.WHITE

        intent.getStringExtra("storeId")?.let {
            viewModel.fetchProductDetail(it)
        } ?: finish()

        setContent {
            SODITheme {
                val bottomSheetState =
                    rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

                val productDetail by viewModel.productResponse.collectAsState()

                ModalBottomSheetLayout(
                    sheetContent = {
                        Column(
                            modifier = Modifier
                                .background(
                                    Color.White,
                                    RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
                                )
                                .padding(horizontal = 24.dp)
                                .padding(bottom = 24.dp)
                        ) {
                            Spacer(modifier = Modifier.size(12.dp))
                            Divider(
                                modifier = Modifier
                                    .fillMaxWidth(.2f)
                                    .align(Alignment.CenterHorizontally),
                                thickness = 3.dp,
                                color = Color.LightGray
                            )
                            Spacer(modifier = Modifier.size(24.dp))
                            Text(
                                text = productDetail?.product ?: "-",
                                color = Color.Black,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "${productDetail?.brand ?: "-"} | ${productDetail?.price ?: "-"}",
                                color = Color.Black,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(modifier = Modifier.size(24.dp))
                            Button(
                                onClick = { viewModel.showProgressPopup.value = true },
                                modifier = Modifier.fillMaxWidth(1f),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF8973D8)
                                )
                            ) {
                                Text(text = "결제하기", fontSize = 20.sp)
                            }
                        }
                    },
                    sheetState = bottomSheetState,
                    sheetBackgroundColor = Color.Transparent
                ) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White)
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Close,
                                contentDescription = "닫기",
                                modifier = Modifier.clickable(onClick = ::finish),
                                tint = Color.Black
                            )
                        }

                        AsyncImage(
                            model = "http://ec2-43-201-75-12.ap-northeast-2.compute.amazonaws.com:8080/postImg/${productDetail?.images?.firstOrNull()}",
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            contentScale = ContentScale.Fit
                        )

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp)
                        ) {
                            Text(text = "${productDetail?.brand}", fontWeight = FontWeight.Medium)
                            Text(
                                text = productDetail?.product ?: "-",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                            )
                            Text(
                                text = productDetail?.price ?: "-",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                            )

                            Spacer(modifier = Modifier.size(8.dp))
                            Divider()
                            Spacer(modifier = Modifier.size(8.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(horizontalAlignment = Alignment.Start) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(
                                            imageVector = Icons.Default.Star,
                                            contentDescription = null,
                                            modifier = Modifier.size(18.dp),
                                            tint = Color(0xFFFF6C37)
                                        )
                                        Text(
                                            text = "리뷰",
                                            color = Color.Black,
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                    Text(
                                        text = "(21)",
                                        color = Color(0xFFBDBDBD),
                                        fontSize = 12.sp
                                    )
                                }
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    val isFavorite by viewModel.isFavorite.collectAsState()

                                    IconButton(onClick = {
                                        viewModel.isFavorite.value = !isFavorite
                                    }) {
                                        Icon(
                                            imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                            contentDescription = "좋아요",
                                            tint = Color(0xFF8973D8)
                                        )
                                    }

                                    Button(
                                        onClick = { viewModel.showBuyPopup.value = true },
                                        modifier = Modifier.fillMaxWidth(0.6f),
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = Color(0xFF8973D8)
                                        )
                                    ) {
                                        Text(text = "주문하기")
                                    }
                                }
                            }
                        }
                    }
                }

                val showProgressPopup by viewModel.showProgressPopup.collectAsState()
                val showCompletePopup by viewModel.showCompletePopup.collectAsState()

                AnimatedVisibility(visible = showProgressPopup || showCompletePopup) {
                    Popup {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color(0x99000000)),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth(.7f)
                                    .background(Color.White, RoundedCornerShape(24.dp))
                                    .padding(28.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                if (showProgressPopup) {
                                    CircularProgressIndicator(modifier = Modifier.size(64.dp))
                                    Spacer(modifier = Modifier.size(24.dp))
                                    Text(
                                        text = "주문 진행 중",
                                        color = Color.Black,
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Normal
                                    )
                                } else {
                                    Text(
                                        text = "결제 완료",
                                        color = Color.Black,
                                        fontSize = 24.sp,
                                        fontWeight = FontWeight.Normal
                                    )
                                }
                            }
                        }
                    }
                }

                LaunchedEffect(bottomSheetState.isVisible) {
                    if (!bottomSheetState.isVisible) {
                        viewModel.showBuyPopup.value = false
                    }
                }

                LaunchedEffect(Unit) {
                    viewModel.showBuyPopup.collectLatest {
                        if (it) {
                            bottomSheetState.show()
                        } else {
                            bottomSheetState.hide()
                        }
                    }
                }

                LaunchedEffect(Unit) {
                    viewModel.showCompletePopup.collectLatest {
                        if (it) {
                            delay(2000)
                            finish()
                        }
                    }
                }
            }
        }
    }
}