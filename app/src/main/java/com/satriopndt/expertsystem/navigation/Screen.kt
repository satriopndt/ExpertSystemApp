package com.satriopndt.expertsystem.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Login : Screen("login")
    object Home : Screen("home")
    object Posting : Screen("posting")
    object Research : Screen("research")
    object Profile: Screen("profile")

    companion object{
        val useBottombar = listOf(
            Home.route,
            Posting.route,
            Research.route,
            Profile.route
        )
    }
}