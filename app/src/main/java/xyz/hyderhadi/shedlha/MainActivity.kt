package xyz.hyderhadi.shedlha

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import xyz.hyderhadi.shedlha.ui.home.HomeScreen
import xyz.hyderhadi.shedlha.ui.theme.SHEDLHATheme
import xyz.hyderhadi.shedlha.ui.util.Screen


class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            SHEDLHATheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Screen.HomeScreen.route,
                        modifier = Modifier
                    ) {
                        composable(Screen.HomeScreen.route) {
                            HomeScreen(
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}