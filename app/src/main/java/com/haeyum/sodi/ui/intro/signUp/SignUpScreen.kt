package com.haeyum.sodi.ui.intro.signUp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.haeyum.sodi.ui.intro.component.IntroComponent
import com.haeyum.sodi.supports.Keyboard
import com.haeyum.sodi.supports.keyboardAsState

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel(),
    onNavigateUp: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color((0xFF222222)))
            .statusBarsPadding()
            .navigationBarsPadding(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .imePadding(),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 24.dp)
            ) {
                IconButton(
                    onClick = onNavigateUp,
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
                Text(
                    text = "Sign Up",
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .clip(AbsoluteRoundedCornerShape(topLeft = 60.dp))
                    .background(color = Color.White)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 36.dp)
                    .padding(top = 40.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val email by viewModel.email.collectAsState()
                val username by viewModel.username.collectAsState()
                val password by viewModel.password.collectAsState()
                val bio by viewModel.bio.collectAsState()

                Column(verticalArrangement = Arrangement.spacedBy(18.dp)) {
                    IntroComponent.CardTextField(
                        type = "Email",
                        hint = "email address",
                        value = email,
                        onValueChange = viewModel::setEmail,
                    )

                    IntroComponent.CardTextField(
                        type = "Username",
                        hint = "username",
                        value = username,
                        onValueChange = viewModel::setUsername,
                    )

                    IntroComponent.CardTextField(
                        type = "Password",
                        hint = "password",
                        value = password,
                        onValueChange = viewModel::setPassword,
                    )

                    IntroComponent.CardTextField(
                        type = "Bio",
                        hint = "biography",
                        value = bio,
                        onValueChange = viewModel::setBio,
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(horizontal = 36.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val keyboardState by keyboardAsState()

                IntroComponent.RoundedButton(
                    text = "Sign Up",
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {

                }

                AnimatedVisibility(visible = keyboardState == Keyboard.Closed) {
                    Column {
                        Spacer(modifier = Modifier.size(24.dp))
                        IntroComponent.NavigateTextButton(
                            text = "Already have any account? ",
                            highlightText = "Sign In",
                            onClick = {}
                        )
                        Spacer(modifier = Modifier.size(24.dp))
                    }
                }
            }
        }
    }
}