package com.satriopndt.expertsystem.ui.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.satriopndt.expertsystem.R
import com.satriopndt.expertsystem.navigation.Screen
import com.satriopndt.expertsystem.ui.theme.BiruDongker
import com.satriopndt.expertsystem.ui.theme.BluePekat
import kotlinx.coroutines.delay

private const val splashDelay: Long = 3000

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    LaunchedEffect(key1 = null) {
        delay(splashDelay)
        navController.popBackStack()
        navController.navigate(Screen.Login.route)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(brush = Brush.verticalGradient(
                colors = listOf(BluePekat, BiruDongker),
                startY = 70f
            )),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = modifier
                .fillMaxSize()
                .padding(20.dp),
            painter = painterResource(id = R.drawable.logo_putih),
            contentDescription = "logo_splash"
        )
    }

}

@Preview
@Composable
fun SplasScreenPreview() {
    SplashScreen(navController = NavHostController(LocalContext.current))

}