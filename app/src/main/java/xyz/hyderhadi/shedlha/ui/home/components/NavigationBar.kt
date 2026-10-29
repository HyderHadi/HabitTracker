package xyz.hyderhadi.shedlha.ui.home.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Checklist
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import xyz.hyderhadi.shedlha.ui.theme.SHEDLHATheme

@Composable
fun AppNavigationBar(
    modifier: Modifier = Modifier
) {
    var selectedTab by rememberSaveable { mutableIntStateOf(0) }
    val tabs = listOf(
        "Home" to Icons.Default.Home,
        "Habits" to Icons.Default.Checklist,
        "Settings" to Icons.Default.Settings
    )

    NavigationBar(
        modifier = modifier
    ) {
        tabs.forEachIndexed { index, (title, icon) ->
            NavigationBarItem(
                selected = selectedTab == index,
                onClick = { selectedTab == index },
                icon = {
                    androidx.compose.material3.Icon(
                        imageVector = icon,
                        contentDescription = title
                    )
                },
                label = {
                    Text(
                        text = title
                    )
                }
            )
        }
    }
}




@Preview
@Composable
fun NavigationBarPreview() {
    SHEDLHATheme() {
        AppNavigationBar()
    }
}