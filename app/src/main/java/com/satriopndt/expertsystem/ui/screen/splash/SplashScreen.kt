package com.satriopndt.expertsystem.ui.screen.splash

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.satriopndt.expertsystem.R
import com.satriopndt.expertsystem.navigation.Screen
import com.satriopndt.expertsystem.ui.theme.BiruDongker
import com.satriopndt.expertsystem.ui.theme.BluePekat
import kotlinx.coroutines.delay

private const val splashDelay: Long = 500
private const val pauseDuration: Long = 1500

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {

    var visible by remember {
        mutableStateOf(false)
    }
    val offsetX by animateDpAsState(
        targetValue = if (visible) 0.dp else (-100).dp,
        animationSpec = tween(durationMillis = splashDelay.toInt())
    )

    LaunchedEffect(key1 = null) {
        delay(splashDelay)
        visible = true
        delay(pauseDuration)
        navController.popBackStack()
        navController.navigate(Screen.Login.route) {
            popUpTo(Screen.Splash.route) {
                inclusive = true
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(BluePekat, BiruDongker),
                    startY = 70f
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = modifier
                .height(80.dp)
                .offset(x = offsetX)
                .alpha(if (visible) 1f else 0f),
            painter = painterResource(id = R.drawable.logo_putih),
            contentDescription = "logo_splash",
            contentScale = ContentScale.Fit
        )
    }

}

@Preview
@Composable
fun SplasScreenPreview() {
    SplashScreen(navController = NavHostController(LocalContext.current))

}