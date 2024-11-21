package com.satriopndt.expertsystem

import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.satriopndt.expertsystem.navigation.Screen
import com.satriopndt.expertsystem.ui.component.BottomBar
import com.satriopndt.expertsystem.ui.screen.add.PostingScreen
import com.satriopndt.expertsystem.ui.screen.home.HomeScreen
import com.satriopndt.expertsystem.ui.screen.login.LoginScreen
import com.satriopndt.expertsystem.ui.screen.profile.ProfileScreen
import com.satriopndt.expertsystem.ui.screen.riset.ResearchScreen
import com.satriopndt.expertsystem.ui.screen.splash.SplashScreen
import com.satriopndt.expertsystem.ui.theme.ExpertSystemTheme

@Composable
fun ExpertSystemApp(
    context: Context = LocalContext.current,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val scaffoldState = rememberScaffoldState()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        bottomBar = {
            if (Screen.useBottombar.contains(currentRoute)) {
                BottomBar(navController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        var destination = Screen.Splash.route
        NavHost(
            navController = navController,
            startDestination = destination,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(Screen.Splash.route) {
                SplashScreen(navController = navController)
            }
            composable(Screen.Login.route) {
                LoginScreen(navController = navController,
                    navigateToHome = {
                        navController.navigate(Screen.Home.route)
                    }
                )
            }
            composable(Screen.Home.route){
                HomeScreen()
            }
            composable(Screen.Posting.route) {
                PostingScreen()
            }
            composable(Screen.Research.route) {
                ResearchScreen()
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
            }
        }

    }

}

@Preview(showBackground = true)
@Composable
fun ExpertSystemPreview() {
    ExpertSystemTheme {
        ExpertSystemApp()
    }
}