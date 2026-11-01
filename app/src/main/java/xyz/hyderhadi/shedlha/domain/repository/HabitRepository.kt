package xyz.hyderhadi.shedlha.domain.repository

import kotlinx.coroutines.flow.Flow
import xyz.hyderhadi.shedlha.domain.model.Habit
import xyz.hyderhadi.shedlha.domain.model.HabitSchedule

interface HabitRepository {

    suspend fun saveHabit(habit: Habit)

    suspend fun deleteHabit(habit: Habit)

    fun getAllHabitStream(): Flow<List<Habit>>

    fun getAllTodayHabits(): Flow<List<Habit>>

    fun getHabitSchedule(habit: Habit): Flow<List<HabitSchedule>>

    suspend fun insertHabitSchedule(schedule: List<HabitSchedule>)
}