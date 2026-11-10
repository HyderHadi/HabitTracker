package xyz.hyderhadi.shedlha.domain.repository

import kotlinx.coroutines.flow.Flow
import xyz.hyderhadi.shedlha.domain.model.Habit
import xyz.hyderhadi.shedlha.domain.model.HabitSchedule

interface HabitRepository {

    suspend fun saveHabit(habit: Habit): Long

    suspend fun deleteHabit(habit: Habit)

    fun getAllHabitStream(): Flow<List<Habit>>

    fun getHabitSchedule(habitId: Long): Flow<List<Int>>

    suspend fun addToHabitSchedule(habitSchedule: HabitSchedule)

    fun getTodayHabits(day: Int): Flow<List<Habit>>

    suspend fun updateHabit(habit: Habit)
}