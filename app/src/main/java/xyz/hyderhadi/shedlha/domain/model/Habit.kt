package xyz.hyderhadi.shedlha.domain.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(tableName = "habit")
data class Habit(
    @PrimaryKey(autoGenerate = true)
    val habitId: Long = 0L,
    val nameOfHabit: String,
    val descriptionOfHabit: String = "",
    val isDone: Boolean = false
)

@Entity(
    tableName = "habit_schedule",
    foreignKeys = [
        ForeignKey(
            entity = Habit::class,
            parentColumns = ["habitId"],
            childColumns = ["habitId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["habitId"]),
        Index(value = ["dayOfWeek"])
    ]
)
data class HabitSchedule(
    @PrimaryKey(autoGenerate = true)
    val habitScheduleId: Long = 0L,
    val habitId: Long,
    val dayOfWeek: Int
)

@Entity(
    tableName = "habit_streak",
    foreignKeys = [
        ForeignKey(
            entity = Habit::class,
            parentColumns = ["habitId"],
            childColumns = ["habitId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["habitId"], unique = true)]
)
data class HabitStreak(
    @PrimaryKey(autoGenerate = true)
    val habitStreakId: Long = 0L,
    val habitId: Long,
    val onStreak: Int = 0
)