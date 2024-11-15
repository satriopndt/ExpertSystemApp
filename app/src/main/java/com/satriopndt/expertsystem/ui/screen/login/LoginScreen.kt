package com.satriopndt.expertsystem.ui.screen.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.satriopndt.expertsystem.R
import com.satriopndt.expertsystem.ui.theme.BiruDongker
import com.satriopndt.expertsystem.ui.theme.BlueDarkLight
import com.satriopndt.expertsystem.ui.theme.BlueLight
import com.satriopndt.expertsystem.ui.theme.BluePekat
import com.satriopndt.expertsystem.ui.theme.LightColor

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    navigateToHome: () -> Unit
) {
    var showLoading by remember {
        mutableStateOf(false)
    }

    var enableButton by remember {
        mutableStateOf(true)
    }

    var visible by remember {
        mutableStateOf(false)
    }

    var text by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var showPassword by remember {
        mutableStateOf(false)
    }

    val focusRequester = remember {
        FocusRequester()
    }

    var isFocused by remember {
        mutableStateOf(false)
    }
    val wasFocused = remember {
        isFocused
    }

    val scrollStateVertical = rememberScrollState()

    val forgetPass = "Change Password"
    val textForgetPass = buildAnnotatedString {
        append("Forget Password?")
        withStyle(
            style = SpanStyle(
                color = Color.Cyan,
                textDecoration = TextDecoration.Underline
            )
        ) {
            pushStringAnnotation(tag = forgetPass, annotation = forgetPass)
            append(forgetPass)
        }
    }


    Column(
        modifier = modifier
            .background(Color.White)
            .fillMaxHeight(1f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = modifier.height(22.dp))
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            //Top Image
            Image(
                painter = painterResource(id = R.drawable.assetrpn),
                contentDescription = "login Image",
                modifier = modifier.height(50.dp)
            )

        }

        Spacer(modifier = Modifier.height(250.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.White, BluePekat, BiruDongker),
                        startY = 70f
                    )
                ),

            ) {
            Image(
                painter = painterResource(id = R.drawable.logo_biru),
                contentDescription = "Logo Biru",
                contentScale = ContentScale.Fit,
                modifier = modifier
                    .height(90.dp)

            )

            /**
             * Email TextField
             */
            Row(
                modifier = modifier.padding(start = 25.dp, end = 25.dp, top = 30.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                OutlinedTextField(
                    value = text,
                    onValueChange = { newId ->
                        text = newId
                    },
                    label = { Text(text = "User Id") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = modifier
                        .focusRequester(focusRequester)
                        .onFocusChanged {
                            isFocused
                        }
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(25.dp),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = colorResource(R.color.white),
                        unfocusedContainerColor = colorResource(R.color.white),
                        disabledContainerColor = colorResource(R.color.white)
                    )

                )
            }

            /**
             * Password TextField
             */
            Row(
                modifier = modifier
                    .padding(start = 25.dp, end = 25.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                OutlinedTextField(value = password,
                    onValueChange = { newPass ->
                        password = newPass
                    },
                    label = {
                        Text(text = "Password")
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    modifier = modifier
                        .focusRequester(focusRequester)
                        .onFocusChanged { isFocused }
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(25.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = colorResource(R.color.white),
                        unfocusedContainerColor = colorResource(R.color.white),
                        disabledContainerColor = colorResource(R.color.white)
                    ),
                    visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        if (showPassword) {
                            IconButton(onClick = { showPassword = false }
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Visibility,
                                    contentDescription = "hide password"
                                )
                            }
                        } else {
                            IconButton(onClick = { showPassword = true }) {
                                Icon(
                                    imageVector = Icons.Filled.VisibilityOff,
                                    contentDescription = "hide_password"
                                )
                            }
                        }
                    }
                )
            }

            Row(
                modifier = modifier
                    .padding(start = 80.dp, end = 80.dp, top = 14.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                ElevatedButton(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(46.dp)
                        .width(200.dp),
                    onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(15.dp),
                    enabled = enableButton,
                    colors = ButtonDefaults.elevatedButtonColors(containerColor = BiruDongker)

                ) {
//                    if (showLoading) {
//                        CircularProgressIndicator(
//                            modifier = modifier.size(24.dp),
//                            color = Color.Gray
//                        )
//                    } else {
                    Text(
                        text = "Continue",
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp,
                        color = Color.White
                    )

                }
            }

            Row(
                modifier = modifier
                    .fillMaxWidth(1f)
                    .padding(top = 10.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                ClickableText(text = textForgetPass, onClick = {})
            }

        }
    }


}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(navController = rememberNavController(),
        navigateToHome = {})
}