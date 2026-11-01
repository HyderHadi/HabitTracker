package xyz.hyderhadi.shedlha.data.data_source

import android.R.attr.version
import androidx.room.Database
import androidx.room.RoomDatabase
import xyz.hyderhadi.shedlha.domain.model.Habit
import xyz.hyderhadi.shedlha.domain.model.HabitCompletion
import xyz.hyderhadi.shedlha.domain.model.HabitSchedule


@Database(entities = [Habit::class, HabitCompletion::class, HabitSchedule::class], version = 1, exportSchema = false)
abstract class HabitsDatabase: RoomDatabase() {

    abstract val habitsDao: HabitsDao

    companion object {
        const val DATABASE_NAME = "habit_db"
    }
}