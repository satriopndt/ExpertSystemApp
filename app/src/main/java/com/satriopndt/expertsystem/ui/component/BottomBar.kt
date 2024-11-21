package com.satriopndt.expertsystem.ui.component

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.satriopndt.expertsystem.R
import com.satriopndt.expertsystem.navigation.NavigationItem
import com.satriopndt.expertsystem.navigation.Screen

@Composable
fun BottomBar(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(
        modifier = modifier
    ) {
        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(R.string.menu_home),
                icon = ImageVector.vectorResource(id = R.drawable.iconamoon_home),
                screen = Screen.Home
            ),
            NavigationItem(
                title = stringResource( R.string.menu_post),
                icon = ImageVector.vectorResource(id = R.drawable.basil_add_outline),
                screen = Screen.Posting
            ),
            NavigationItem(
                title = stringResource(R.string.menu_researcher),
                icon = ImageVector.vectorResource(id = R.drawable.uim_analytics),
                screen = Screen.Research
            ),
            NavigationItem(
                title = stringResource( R.string.menu_profile),
                icon = ImageVector.vectorResource(id = R.drawable.iconamoon_profile_light),
                screen = Screen.Profile
            )
        )
        navigationItems.map { item ->
            NavigationBarItem(
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = { Text(text = item.title)}
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun BottomBarPreview(){
//    BottomBar(navController = rememberNavController())
//
//}