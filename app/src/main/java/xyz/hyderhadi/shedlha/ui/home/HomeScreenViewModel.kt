package xyz.hyderhadi.shedlha.ui.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import xyz.hyderhadi.shedlha.domain.model.Habit
import xyz.hyderhadi.shedlha.domain.use_case.HabitUseCase
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
class HomeScreenViewModel(
    private val habitUseCase: HabitUseCase
): ViewModel() {

    private var _uiState = MutableStateFlow(HomeScreenUiState())
    val uiState: StateFlow<HomeScreenUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            habitUseCase.getTodayHabitsUseCase(day = LocalDate.now().dayOfWeek.value % 7 + 1).collect { habits ->
                _uiState.update { state ->
                    state.copy(
                        todayHabits = habits
                    )
                }
            }
        }
    }
    fun updateHabitDoneState(habit: Habit) {
        viewModelScope.launch {
            habitUseCase.updateHabitUseCase(
                habit = habit.copy(
                    isDone = !habit.isDone
                )
            )
        }
    }
}