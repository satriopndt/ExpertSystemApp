package com.satriopndt.expertsystem

import android.content.Context
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.satriopndt.expertsystem.ui.screen.login.LoginScreen
import com.satriopndt.expertsystem.ui.theme.ExpertSystemTheme

@Composable
fun ExpertSystemApp(
    context: Context = LocalContext.current,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    LoginScreen(navController = rememberNavController()) {

    }
}

@Preview(showBackground = true)
@Composable
fun ExpertSystemPreview() {
    ExpertSystemTheme {
        ExpertSystemApp()
    }
}