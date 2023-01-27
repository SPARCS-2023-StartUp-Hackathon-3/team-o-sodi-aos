package com.haeyum.sodi.ui.intro.signIn

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.haeyum.sodi.R
import com.haeyum.sodi.ui.intro.component.IntroComponent.CardTextField
import com.haeyum.sodi.ui.intro.component.IntroComponent.RoundedButton

@Composable
fun SignInScreen(
    viewModel: SignInViewModel = hiltViewModel(),
    navigateToSignUp: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color((0xFF222222)))
            .statusBarsPadding()
            .imePadding()
            .navigationBarsPadding()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.3f),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.logo_white),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(0.35f)
                )
                Text(
                    text = "Share your Codi.",
                    modifier = Modifier.padding(top = 12.dp),
                    color = Color.White,
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Center
                )
            }
        }
        Column(
            modifier = Modifier
                .weight(0.7f)
                .clip(AbsoluteRoundedCornerShape(topLeft = 60.dp))
                .background(color = Color.White)
        ) {
            Text(
                text = "Sign In",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(48.dp),
                color = Color.Black,
                fontSize = 32.sp,
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.Center
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 36.dp)
            ) {
                val email by viewModel.email.collectAsState()
                val password by viewModel.password.collectAsState()

                Column(modifier = Modifier.fillMaxWidth()) {
                    CardTextField(
                        type = "Email",
                        hint = "email address",
                        value = email,
                        onValueChange = viewModel::setEmail,
                    )
                    Spacer(modifier = Modifier.height(18.dp))
                    CardTextField(
                        type = "Password",
                        hint = "password",
                        value = password,
                        onValueChange = viewModel::setPassword,
                        visualTransformation = PasswordVisualTransformation(),
                    )
                }

                RoundedButton(
                    text = "Sign In",
                    modifier = Modifier
                        .padding(top = 36.dp)
                        .fillMaxWidth(),
                    enabled = viewModel.enabledSignIn.collectAsState().value
                ) {
                    viewModel.requestPostSignUp()
                }
                Spacer(modifier = Modifier.weight(1f))
                TextButton(
                    onClick = navigateToSignUp,
                    modifier = Modifier
                        .align(CenterHorizontally)
                        .padding(bottom = 24.dp)
                ) {
                    Text(
                        text = buildAnnotatedString {
                            append("Don't have any account? ")
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Medium)) {
                                append("Sign Up")
                            }
                        },
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal
                    )
                }
            }
        }
    }
}