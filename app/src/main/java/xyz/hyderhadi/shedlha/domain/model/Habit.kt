package xyz.hyderhadi.shedlha.domain.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(tableName = "habit")
data class Habit(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val nameOfHabit: String,
    val descriptionOfHabit: String = "",
    val isDone: Boolean = false,
    val isHabitForToday: Boolean = false
)

@Entity(
    tableName = "habit_completion",
    foreignKeys = [
        ForeignKey(
            entity = Habit::class,
            parentColumns = ["id"],
            childColumns = ["habitId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("habitId")],
    primaryKeys = ["habitId", "date"]
)
data class HabitCompletion(
    val habitId: Long,
    val date: String
)

@Entity(
    tableName = "habit_schedule",
    foreignKeys = [
        ForeignKey(
            entity = Habit::class,
            parentColumns = ["id"],
            childColumns = ["habitId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("habitId")],
    primaryKeys = ["habitId", "dayOfWeek"]
)
data class HabitSchedule(
    val habitId: Long,
    val dayOfWeek: Int
)