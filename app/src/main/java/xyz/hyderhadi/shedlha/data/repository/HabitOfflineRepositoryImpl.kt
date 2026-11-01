package xyz.hyderhadi.shedlha.data.repository

import kotlinx.coroutines.flow.Flow
import xyz.hyderhadi.shedlha.data.data_source.HabitsDao
import xyz.hyderhadi.shedlha.domain.model.Habit
import xyz.hyderhadi.shedlha.domain.model.HabitSchedule
import xyz.hyderhadi.shedlha.domain.repository.HabitRepository

class HabitOfflineRepositoryImpl(
    private val habitsDao: HabitsDao
): HabitRepository {

    override suspend fun saveHabit(habit: Habit) {
        habitsDao.saveHabit(habit = habit)
    }

    override suspend fun deleteHabit(habit: Habit) {
        habitsDao.deleteHabit(habit = habit)
    }

    override fun getAllHabitStream(): Flow<List<Habit>> {
        return habitsDao.getAllHabits()
    }

    override fun getAllTodayHabits(): Flow<List<Habit>> {
        return habitsDao.getAllTodayHabits()
    }

    override fun getHabitSchedule(habit: Habit): Flow<List<HabitSchedule>> {
        return habitsDao.getHabitSchedule(habit = habit)
    }

    override suspend fun insertHabitSchedule(schedule: List<HabitSchedule>) {
        habitsDao.insertHabitSchedule(schedule = schedule)
    }
}