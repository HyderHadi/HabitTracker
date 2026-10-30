package xyz.hyderhadi.shedlha.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import xyz.hyderhadi.shedlha.domain.model.Habit
import xyz.hyderhadi.shedlha.domain.use_case.HabitUseCase

class HomeScreenViewModel(
    private val habitUseCase: HabitUseCase
): ViewModel() {

    private var _uiState = MutableStateFlow(HomeScreenUiState())
    val uiState: StateFlow<HomeScreenUiState> = _uiState.asStateFlow()



    fun updateHabitDoneState(habit: Habit) {
        viewModelScope.launch {
            habitUseCase.addHabitUseCase(
                habit.copy(
                    isDone = !habit.isDone
                )
            )
        }
    }
}