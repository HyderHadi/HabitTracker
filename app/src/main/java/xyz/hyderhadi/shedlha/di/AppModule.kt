package xyz.hyderhadi.shedlha.di

import android.content.Context
import androidx.room.Room
import xyz.hyderhadi.shedlha.data.data_source.HabitsDatabase
import xyz.hyderhadi.shedlha.data.repository.HabitOfflineRepositoryImpl
import xyz.hyderhadi.shedlha.domain.repository.HabitRepository
import xyz.hyderhadi.shedlha.domain.use_case.AddHabitScheduleUseCase
import xyz.hyderhadi.shedlha.domain.use_case.AddHabitUseCase
import xyz.hyderhadi.shedlha.domain.use_case.DeleteHabitUseCase
import xyz.hyderhadi.shedlha.domain.use_case.GetAllHabitsUseCase
import xyz.hyderhadi.shedlha.domain.use_case.GetHabitScheduleUseCase
import xyz.hyderhadi.shedlha.domain.use_case.GetTodayHabitsUseCase
import xyz.hyderhadi.shedlha.domain.use_case.HabitUseCase
import xyz.hyderhadi.shedlha.domain.use_case.UpdateHabitUseCase


interface AppModule {
    val habitsDatabase: HabitsDatabase
    val habitRepository: HabitRepository
    val habitUseCase: HabitUseCase
}
class AppModuleImpl(
    private val context: Context
): AppModule {


    override val habitsDatabase: HabitsDatabase by lazy {
        Room.databaseBuilder(
            context = context,
            klass = HabitsDatabase::class.java,
            name = HabitsDatabase.DATABASE_NAME
        ).build()
    }

    override val habitRepository: HabitRepository by lazy {
        HabitOfflineRepositoryImpl(habitsDatabase.habitsDao)
    }

    override val habitUseCase: HabitUseCase by lazy {
        HabitUseCase(
            addHabitUseCase = AddHabitUseCase(habitRepository),
            deleteHabitUseCase = DeleteHabitUseCase(habitRepository),
            getAllHabitsUseCase = GetAllHabitsUseCase(habitRepository),
            addHabitScheduleUseCase = AddHabitScheduleUseCase(habitRepository),
            habitScheduleUseCase = GetHabitScheduleUseCase(habitRepository),
            getTodayHabitsUseCase = GetTodayHabitsUseCase(habitRepository),
            updateHabitUseCase = UpdateHabitUseCase(habitRepository)
        )
    }
}