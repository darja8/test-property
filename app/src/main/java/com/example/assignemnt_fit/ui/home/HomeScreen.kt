import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
import androidx.compose.material3.Text
import com.example.assignemnt_fit.ui.navigation.screens

@Composable
fun ExerciseScreen(
    navController: NavHostController
) {
    val coroutineScope = rememberCoroutineScope()

    TopLevelScaffold(
        navController = navController,
        floatingActionButton = {
            screens.forEach { screen ->
                ExtendedFloatingActionButton(
                    onClick = {
                        navController.navigate(screen.route) {
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
            }
        },
        coroutineScope = coroutineScope
    )
    {innerPadding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            modifier = Modifier
                .padding(start = 4.dp, bottom = 4.dp, top = 65.dp)
//                .fillMaxHeight()
        ) {
            items(days) {
                DayCard(
                    day = it,
                    modifier = Modifier
                        .padding(end = 4.dp, top = 4.dp),
                )
            }
        }
    }
}

@Preview
@Composable
private fun ExerciseScreenPreview() {
    val navController = rememberNavController()
    Assignemnt_fitTheme(dynamicColor = false) {
        ExerciseScreen(navController)
    }
}