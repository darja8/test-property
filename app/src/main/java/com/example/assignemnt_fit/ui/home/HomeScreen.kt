import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.assignemnt_fit.R
import com.example.assignemnt_fit.model.days
import com.example.assignemnt_fit.ui.components.*
import com.example.assignemnt_fit.ui.theme.Assignemnt_fitTheme
import com.example.assignemnt_fit.ui.components.TopLevelScaffold
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowCompat
import com.example.assignemnt_fit.model.WeekDay
import com.example.assignemnt_fit.ui.navigation.Screen

@SuppressLint("Range")
@Composable
fun HomeScreen(
    navController: NavHostController,
    topAppBarColor: Color
) {
    val context = LocalContext.current
    val window = (context as? Activity)?.window

    SideEffect {
        window?.let {
            WindowCompat.getInsetsController(it, it.decorView)?.let { controller ->
                controller.isAppearanceLightStatusBars = true // Adjust based on theme
                it.statusBarColor = topAppBarColor.toArgb()
            }
        }
    }
    val coroutineScope = rememberCoroutineScope()
    TopLevelScaffold(
        navController = navController,
        floatingActionButton = {
//            screens.forEach { screen ->
                ExtendedFloatingActionButton(
                    onClick = {
                        navController.navigate(Screen.Exercises.route) {
                        }
                    },
                    modifier = Modifier.padding()
                ) {
                    Icon(
                        imageVector = Icons.Filled.List,
                        contentDescription = stringResource(R.string.exercise_list)
                    )
                    Text(
                        text = "Exercises",
                        modifier = Modifier.padding(start = 10.dp)
                    )
                }
//            }
        },
        coroutineScope = coroutineScope
    )
    {

        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            modifier = Modifier
                .padding(start = 4.dp, bottom = 4.dp, top = 65.dp)
        ) {
            items(days) { day ->
                DayScreenContent(day = day) {
                    navController.navigate("${Screen.Day.route}/${day.dayId}")
                }
            }
        }
    }
}

@Composable
private fun DayScreenContent(day: WeekDay, clickAction: () -> Unit){
    DayCard(
        day = day,
        modifier = Modifier
            .padding(end = 4.dp, top = 4.dp)
            .clickable(onClick = { clickAction.invoke() }),
    )
}

@Preview
@Composable
private fun ExerciseScreenPreview() {
    val navController = rememberNavController()
    Assignemnt_fitTheme(dynamicColor = false) {
        HomeScreen(navController = navController, topAppBarColor = MaterialTheme.colorScheme.onSecondary)
    }
}