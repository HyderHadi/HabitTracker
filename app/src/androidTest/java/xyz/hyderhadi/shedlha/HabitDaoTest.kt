package xyz.hyderhadi.shedlha

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import xyz.hyderhadi.shedlha.data.data_source.HabitsDao
import xyz.hyderhadi.shedlha.data.data_source.HabitsDatabase
import xyz.hyderhadi.shedlha.domain.model.Habit
import xyz.hyderhadi.shedlha.domain.model.HabitSchedule


@RunWith(AndroidJUnit4::class)
class HabitDaoTest {
    private lateinit var db: HabitsDatabase
    private lateinit var dao: HabitsDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        db = Room.inMemoryDatabaseBuilder(
            context = context,
            HabitsDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = db.habitsDao
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun insertHabit_thenReadItBack() = runBlocking {
        val habit = Habit(
            nameOfHabit = "Drink Water",
            descriptionOfHabit = "At afternoon"
        )

        dao.saveHabit(habit = habit)
        val saved = dao.getAllHabits().first()

        assertEquals(1, saved.size)
        assertEquals(1, saved.single().habitId)
        assertEquals("Drink Water", saved.single().nameOfHabit)
    }

    @Test
    fun insertSchedule_thenReadItBack() = runBlocking {

        val habit = Habit(
            nameOfHabit = "Drink Water",
            descriptionOfHabit = "At afternoon"
        )

        val habit2 = Habit(
            nameOfHabit = "dkkasdksakdk",
            descriptionOfHabit = "At afternoon"
        )

        dao.saveHabit(habit = habit)
        dao.saveHabit(habit = habit2)

        dao.addToHabitSchedule(
            HabitSchedule(
                dayOfWeek = 3,
                habitId = 1L
            )
        )

        dao.addToHabitSchedule(
            HabitSchedule(
                dayOfWeek = 2,
                habitId = 1L
            )
        )

        dao.addToHabitSchedule(
            HabitSchedule(
                dayOfWeek = 2,
                habitId = 2L
            )
        )

        val saved = dao.getHabitSchedule(1L).first()
        assertEquals(2, saved.size)
        assertFalse(saved.contains(5))


        val scheduled = dao.getTodayHabits(2).first()
        assertEquals(2,scheduled.size)
        assertFalse(scheduled.size == 1)
    }
}