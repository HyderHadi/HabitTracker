package xyz.hyderhadi.shedlha.ui.home.components

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import xyz.hyderhadi.shedlha.R
import xyz.hyderhadi.shedlha.domain.model.Habit
import xyz.hyderhadi.shedlha.ui.AppNavigationBar
import xyz.hyderhadi.shedlha.ui.home.HomeScreenUiState
import xyz.hyderhadi.shedlha.ui.home.HomeScreenViewModel
import xyz.hyderhadi.shedlha.ui.theme.AppTypography
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.random.Random

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    uiState: HomeScreenUiState,
    viewModel: HomeScreenViewModel,
    navController: NavController
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        topBar = {
            HomeScreenTopBar(
                modifier = Modifier
            )
        },
        bottomBar = {
            AppNavigationBar(
                navController = navController
            )
        }
    ) { innerPadding ->
        TodayContent(
            modifier = Modifier
                .padding(innerPadding),
            uiState = uiState,
            viewModel = viewModel
        )
    }
}


@Composable
fun HomeScreenTopBar(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .statusBarsPadding()
    ) {
        Text(
            text = stringResource(R.string.home_screen_title),
            style = AppTypography.titleLarge
        )
    }
}

@SuppressLint("RememberReturnType")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TodayContent(
    modifier: Modifier = Modifier,
    uiState: HomeScreenUiState,
    viewModel: HomeScreenViewModel
) {

    val progressValue = uiState.todayHabits.count { it.isDone }.toFloat() /
            uiState.todayHabits.size
    val habitDoneCount = uiState.todayHabits.count { it.isDone }
    val progressLabel = when {
        uiState.todayHabits.isEmpty() -> "Add a habit to get started!"
        progressValue == 0f -> "Every great day starts with one small step."
        progressValue < 0.5f -> "Nice start—keep the momentum going!"
        progressValue < 1f -> "You’re more than halfway there. Keep it up!"
        else -> "Amazing—you completed all your habits today!"
    }
    Column(
        modifier = modifier
            .padding(8.dp),
        verticalArrangement = Arrangement.Center
    ) {
        DateAndTip(
            date = currentDay.format(DateTimeFormatter.ofPattern("EEEE, MMMM d")),
            tipOfTheDay = remember(currentDay) {
                tipOfTheDayList[Random(currentDay.toEpochDay()).nextInt(tipOfTheDayList.size)]
            }
        )
        DailyProgress(
            habitDoneCount = habitDoneCount,
            progress = progressValue,
            progressLabel = progressLabel,
            todayHabitTotalCount = uiState.todayHabits.size
        )
        TodaySectionHeading(
            countOfTodayHabit = uiState.todayHabits.size
        )
        HabitsTodayColumn(
            habits = uiState.todayHabits,
            modifier = Modifier.weight(1f),
            viewModel = viewModel
        )
        CompletionGuidance(
            modifier = Modifier
        )
    }
}

@Composable
fun DateAndTip(
    date: String,
    tipOfTheDay: String
) {

    Text(
        text = date,
        style = AppTypography.bodyMedium,
        color = Color.Gray
    )
    Text(
        text = tipOfTheDay,
        style = AppTypography.displaySmall
    )
}

@Composable
fun DailyProgress(
    modifier: Modifier = Modifier,
    habitDoneCount: Int,
    progress: Float,
    progressLabel: String,
    todayHabitTotalCount: Int
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.today_progress_label),
                    style = AppTypography.titleMedium
                )
                Text(
                    text = "$habitDoneCount of $todayHabitTotalCount",
                    style = AppTypography.titleMedium,
                    color = MaterialTheme.colorScheme.primaryContainer
                )
            }

            LinearProgressIndicator(
                progress = { progress.coerceIn(0f, 1f) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .height(8.dp),
                strokeCap = StrokeCap.Square,
                trackColor = Color.Gray,
                color = MaterialTheme.colorScheme.primaryContainer
            )

            Text(
                text = progressLabel,
                modifier = Modifier
                    .padding(8.dp),
                style = AppTypography.titleSmall,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun TodaySectionHeading(
    modifier: Modifier = Modifier,
    countOfTodayHabit: Int
) {
    Row(
        modifier = modifier
            .padding(2.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Today's habits",
            style = AppTypography.titleMedium
        )
        Text(
            text = "$countOfTodayHabit scheduled",
            style = AppTypography.titleMedium,
            color = Color.Gray
        )
    }
}

@Composable
fun HabitsTodayColumn(
    modifier: Modifier = Modifier,
    habits: List<Habit>,
    viewModel: HomeScreenViewModel
) {

    LazyColumn(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .heightIn(max = 400.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(habits) { habit ->
            TodayHabitRow(
                habit = habit,
                onCheckChange = {
                    viewModel.updateHabitDoneState(habit)
                }
            )
        }
    }
}

@Composable
fun TodayHabitRow(
    modifier: Modifier = Modifier,
    habit: Habit,
    onCheckChange: () -> Unit = { }
) {
    Row(
        modifier = modifier
            .padding(4.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = habit.nameOfHabit,
                style = AppTypography.bodyLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
            )
            Text(
                text = "${habit.descriptionOfHabit} . ${if (habit.isDone) "Done" else "Not done yet"}",
                style = AppTypography.bodyMedium,
                color = Color.Gray,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
            )
        }
        Checkbox(
            checked = habit.isDone,
            onCheckedChange = {
                onCheckChange()
            },
            modifier = Modifier,
            colors = CheckboxDefaults.colors(
                checkedColor = MaterialTheme.colorScheme.primaryContainer,
                uncheckedColor = MaterialTheme.colorScheme.primaryContainer
            )
        )
    }
}

@Composable
fun CompletionGuidance(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        HorizontalDivider(
            modifier = modifier
                .fillMaxWidth()
        )
        Text(
            text = "Tap a checkbox when you're done. You can always uncheck it.",
            style = AppTypography.bodyMedium,
            color = Color.Gray
        )
    }
}


@RequiresApi(Build.VERSION_CODES.O)
val currentDay: LocalDate = LocalDate.now()

val tipOfTheDayList = listOf(
    "Start small. Keep going.",
    "One habit at a time.",
    "Progress beats perfection.",
    "You’ve got this!",
    "Keep the momentum going.",
    "Every step counts.",
    "Look how far you’ve come.",
    "Finish strong!"
)