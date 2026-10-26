package xyz.hyderhadi.shedlha.domain.use_case

data class HabitUseCase(
    val addHabitUseCase: AddHabitUseCase,
    val deleteHabitUseCase: DeleteHabitUseCase,
    val getAllHabitsUseCase: GetAllHabitsUseCase
)
