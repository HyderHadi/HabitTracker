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
import xyz.hyderhadi.shedlha.ui.home.HomeScreen
import xyz.hyderhadi.shedlha.ui.theme.SHEDLHATheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SHEDLHATheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    HomeScreen()
                }
            }
        }
    }
}