package xyz.hyderhadi.shedlha.ui.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Checklist
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
    val icon: ImageVector?,
    val label: String
) {
    object HomeScreen: Screen("home_screen", Icons.Default.Home, "Home")
    object HabitsScreen: Screen("habits_screen", Icons.Default.Checklist, "Habits")
    object HabitDetails: Screen("habit_details", null, "Habits Details")
    object SettingsScreen: Screen("settings_screen", Icons.Default.Settings, "Settings")
}