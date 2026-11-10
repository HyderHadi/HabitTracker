package xyz.hyderhadi.shedlha.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import xyz.hyderhadi.shedlha.domain.model.Habit
import xyz.hyderhadi.shedlha.domain.model.HabitSchedule

@Dao
interface HabitsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveHabit(habit: Habit): Long

    @Delete
    suspend fun deleteHabit(habit: Habit)

    @Update
    suspend fun updateHabit(habit: Habit)

    @Query("SELECT * FROM habit")
    fun getAllHabits(): Flow<List<Habit>>

    @Query("""
        SELECT dayOfWeek FROM habit_schedule INNER JOIN habit ON habit_schedule.habitId = habit.habitId WHERE habit.habitId = :habitId
    """)
    fun getHabitSchedule(habitId: Long): Flow<List<Int>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToHabitSchedule(habitSchedule: HabitSchedule)

    @Query("""
        SELECT * FROM habit INNER JOIN habit_schedule ON habit.habitId = habit_schedule.habitId WHERE habit_schedule.dayOfWeek = :day
    """)
    fun getTodayHabits(day: Int): Flow<List<Habit>>
}