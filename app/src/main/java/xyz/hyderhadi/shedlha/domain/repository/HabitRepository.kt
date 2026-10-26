package xyz.hyderhadi.shedlha.domain.repository

import kotlinx.coroutines.flow.Flow
import xyz.hyderhadi.shedlha.domain.model.Habit

interface HabitRepository {

    suspend fun saveHabit(habit: Habit)

    suspend fun deleteHabit(habit: Habit)

    fun getAllHabitStream(): Flow<List<Habit>>
}