package xyz.hyderhadi.shedlha.ui.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import xyz.hyderhadi.shedlha.R
import xyz.hyderhadi.shedlha.domain.model.Habit
import xyz.hyderhadi.shedlha.ui.theme.AppTypography
import xyz.hyderhadi.shedlha.ui.theme.SHEDLHATheme

@Composable
fun HomeContent(
    modifier: Modifier = Modifier
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
            AppNavigationBar()
        }
    ) { innerPadding ->

        TodayContent(
            modifier = Modifier
                .padding(innerPadding)
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
    ) {
        Text(
            text = stringResource(R.string.home_screen_title),
            style = AppTypography.titleLarge
        )
    }
}

@Composable
fun TodayContent(
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier
            .padding(8.dp),
        verticalArrangement = Arrangement.Center
    ) {
        DateAndTip(
            date = "Monday, October 26",
            tipOfTheDay = "Go get it, Champ"
        )
        DailyProgress(
            habitDoneCount = "2 of 4",
            progress = 0.5f,
            progressLabel = "Halfway there, Keep it going"
        )
        TodaySectionHeading(
            countOfTodayHabit = 4
        )
        HabitsTodayColumn(
            habits = listOf(
                Habit(
                    id = 1,
                    nameOfHabit = "Drink water",
                    descriptionOfHabit = "1 glass after walking",
                    isDone = true
                ),
                Habit(
                    id = 2,
                    nameOfHabit = "Walk for 20 minutes",
                    descriptionOfHabit = "1 glass after walking",
                    isDone = true
                ),
                Habit(
                    id = 2,
                    nameOfHabit = "Walk for 20 minutes",
                    descriptionOfHabit = "Evening",
                    isDone = false
                ),
                Habit(
                    id = 2,
                    nameOfHabit = "Stretch for 5 minutes",
                    descriptionOfHabit = "Before bed",
                    isDone = false
                ),
                Habit(
                    id = 2,
                    nameOfHabit = "Stretch for 5 minutes",
                    descriptionOfHabit = "Before bed",
                    isDone = false
                ),
                Habit(
                    id = 2,
                    nameOfHabit = "Stretch for 5 minutes",
                    descriptionOfHabit = "Before bed",
                    isDone = false
                )
            )
        )
        CompletionGuidance(
            modifier = Modifier
        )
    }
}

@Composable
fun DateAndTip(
    modifier: Modifier = Modifier,
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
    habitDoneCount: String,
    progress: Float,
    progressLabel: String
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
                    text = habitDoneCount,
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
                style = AppTypography.titleMedium,
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
    habits: List<Habit>
) {

    LazyColumn(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .heightIn(max = 400.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(habits) { habit ->
            TodayHabitRow(habit = habit)
        }
    }
}

@Composable
fun TodayHabitRow(
    modifier: Modifier = Modifier,
    habit: Habit
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
            onCheckedChange = { !habit.isDone },
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
                .padding(8.dp)
                .fillMaxWidth()
        )
        Text(
            text = "Tap a checkbox when you're done. You can always uncheck it.",
            style = AppTypography.bodyMedium,
            color = Color.Gray
        )
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    SHEDLHATheme {
        HomeContent()
    }
}