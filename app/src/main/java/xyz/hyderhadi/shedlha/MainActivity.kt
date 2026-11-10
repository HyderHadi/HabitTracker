package xyz.hyderhadi.shedlha

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import xyz.hyderhadi.shedlha.ui.AppNavigationBar
import xyz.hyderhadi.shedlha.ui.habits.HabitsScreen
import xyz.hyderhadi.shedlha.ui.home.HomeScreen
import xyz.hyderhadi.shedlha.ui.settings.SettingsScreen
import xyz.hyderhadi.shedlha.ui.theme.SHEDLHATheme
import xyz.hyderhadi.shedlha.ui.util.Screen


class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            SHEDLHATheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize(),
                    bottomBar = {
                        AppNavigationBar(
                            navController = navController
                        )
                    }
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Screen.HomeScreen.route,
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        composable(Screen.HomeScreen.route) {
                            HomeScreen()
                        }
                        composable(Screen.HabitsScreen.route) {
                            HabitsScreen()
                        }
                        composable(Screen.SettingsScreen.route) {
                            SettingsScreen()
                        }
                    }
                }
            }
        }
    }
}