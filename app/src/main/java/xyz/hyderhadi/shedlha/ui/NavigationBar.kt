package xyz.hyderhadi.shedlha.ui

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import xyz.hyderhadi.shedlha.ui.util.Screen

@Composable
fun AppNavigationBar(
    modifier: Modifier = Modifier,
    navController: NavController
) {

    val screens = listOf(
        Screen.HomeScreen,
        Screen.HabitsScreen,
        Screen.HabitDetails,
        Screen.SettingsScreen
    )

    val currentRoute = navController.currentBackStackEntryAsState()
        .value?.destination?.route

    NavigationBar(
        modifier = modifier
    ) {
        screens
            .filter { it != Screen.HabitDetails }
            .forEach { screen ->
            NavigationBarItem(
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    screen.icon?.let {
                        Icon(
                            imageVector = it,
                            contentDescription = screen.label
                        )
                    }
                },
                label = {
                    Text(
                        text = screen.label
                    )
                }
            )
        }
    }
}