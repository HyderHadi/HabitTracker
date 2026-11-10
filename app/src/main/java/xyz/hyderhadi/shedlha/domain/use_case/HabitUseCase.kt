package xyz.hyderhadi.shedlha.domain.use_case

data class HabitUseCase(
    val addHabitUseCase: AddHabitUseCase,
    val deleteHabitUseCase: DeleteHabitUseCase,
    val getAllHabitsUseCase: GetAllHabitsUseCase,
    val addHabitScheduleUseCase: AddHabitScheduleUseCase,
    val habitScheduleUseCase: GetHabitScheduleUseCase,
    val getTodayHabitsUseCase: GetTodayHabitsUseCase,
    val updateHabitUseCase: UpdateHabitUseCase
)
