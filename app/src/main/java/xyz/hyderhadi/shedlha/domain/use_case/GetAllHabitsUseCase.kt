package xyz.hyderhadi.shedlha.domain.use_case

import kotlinx.coroutines.flow.Flow
import xyz.hyderhadi.shedlha.domain.model.Habit
import xyz.hyderhadi.shedlha.domain.repository.HabitRepository

class GetAllHabitsUseCase(
    private val repository: HabitRepository
) {

    operator fun invoke(): Flow<List<Habit>> {
        return repository.getAllHabitStream()
    }
}