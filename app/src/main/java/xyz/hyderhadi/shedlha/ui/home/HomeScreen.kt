package xyz.hyderhadi.shedlha.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import xyz.hyderhadi.shedlha.HabitApp
import xyz.hyderhadi.shedlha.ui.viewModelFactory


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeScreenViewModel = viewModel(factory = viewModelFactory {
        HomeScreenViewModel(HabitApp.appModule.habitUseCase)
    })
) {


}