package xyz.hyderhadi.shedlha.data.repository

import kotlinx.coroutines.flow.Flow
import xyz.hyderhadi.shedlha.data.data_source.HabitsDao
import xyz.hyderhadi.shedlha.domain.model.Habit
import xyz.hyderhadi.shedlha.domain.model.HabitSchedule
import xyz.hyderhadi.shedlha.domain.repository.HabitRepository

class HabitOfflineRepositoryImpl(
    private val habitsDao: HabitsDao
): HabitRepository {


    override fun getAllHabitStream(): Flow<List<Habit>> {
        return habitsDao.getAllHabits()
    }

    override suspend fun saveHabit(habit: Habit): Long {
        return habitsDao.saveHabit(habit)
    }

    override suspend fun deleteHabit(habit: Habit) {
        habitsDao.deleteHabit(habit)
    }

    override fun getHabitSchedule(habitId: Long): Flow<List<Int>> {
        return habitsDao.getHabitSchedule(habitId)
    }

    override suspend fun addToHabitSchedule(habitSchedule: HabitSchedule) {
        habitsDao.addToHabitSchedule(habitSchedule)
    }

    override fun getTodayHabits(day: Int): Flow<List<Habit>> {
        return habitsDao.getTodayHabits(day)
    }

    override suspend fun updateHabit(habit: Habit) {
        habitsDao.updateHabit(habit)
    }
}