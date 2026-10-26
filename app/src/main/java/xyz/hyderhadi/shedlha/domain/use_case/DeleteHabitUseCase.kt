package xyz.hyderhadi.shedlha.domain.use_case

import xyz.hyderhadi.shedlha.domain.model.Habit
import xyz.hyderhadi.shedlha.domain.repository.HabitRepository

class DeleteHabitUseCase(
    private val repository: HabitRepository
) {

    suspend operator fun invoke(habit: Habit) {
        repository.deleteHabit(habit = habit)
    }
}