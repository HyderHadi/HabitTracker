package xyz.hyderhadi.shedlha.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import xyz.hyderhadi.shedlha.domain.model.Habit


@Database(entities = [Habit::class], version = 1, exportSchema = false)
abstract class HabitsDatabase: RoomDatabase() {

    abstract val habitsDao: HabitsDao

    companion object {
        const val DATABASE_NAME = "habit_db"
    }
}