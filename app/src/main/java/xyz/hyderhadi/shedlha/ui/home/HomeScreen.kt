package xyz.hyderhadi.shedlha.ui.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import xyz.hyderhadi.shedlha.HabitApp
import xyz.hyderhadi.shedlha.domain.model.Habit
import xyz.hyderhadi.shedlha.ui.home.components.HomeContent
import xyz.hyderhadi.shedlha.ui.viewModelFactory

data class HomeScreenUiState(
    val todayHabits: List<Habit> = emptyList()
)


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeScreenViewModel = viewModel(factory = viewModelFactory {
        HomeScreenViewModel(HabitApp.appModule.habitUseCase)
    })
) {
    val state by viewModel.uiState.collectAsState()

    HomeContent(
        uiState = state,
        viewModel = viewModel
    )
}