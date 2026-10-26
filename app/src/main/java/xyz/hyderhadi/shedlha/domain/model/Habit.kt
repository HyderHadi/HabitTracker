package xyz.hyderhadi.shedlha.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "habit")
data class Habit(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val nameOfHabit: String,
    val descriptionOfHabit: String = "",
    val isDone: Boolean = false
)