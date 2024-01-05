
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DefaultShadowColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.assignemnt_fit.R
import com.example.assignemnt_fit.model.Exercise
import com.example.assignemnt_fit.model.ExercisesViewModel
import com.example.assignemnt_fit.ui.components.SubpageScaffold
import com.example.assignemnt_fit.ui.theme.Assignemnt_fitTheme


@Composable
fun AddNewExerciseScreen(
    navController: NavHostController,
    exercisesViewModel: ExercisesViewModel = viewModel() // Get the ViewModel

) {
    SubpageScaffold(navController = navController, title = "New Exercise")
    {
        Column {
            val modifier = Modifier
            var exerciseTitle by remember { mutableStateOf("") }
            var numberOfSets by remember { mutableStateOf("") }
            var numberOfReps by remember { mutableStateOf("") }
            var weight by remember { mutableStateOf("") }
            var timeInMinutes by remember { mutableStateOf("") }
            var isDropset by remember { mutableStateOf(false) }

            Row (
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
            ){
                Box(modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .padding(bottom = 60.dp, end = 30.dp)
                ){
                    Image(painter = painterResource(id = R.drawable.empty_photo),
                        contentDescription = "workout image",
                        contentScale = ContentScale.FillHeight,
                        modifier = Modifier
                            .padding(top = 90.dp, start = 30.dp)
                            .height(120.dp)
                            .width(120.dp)
                            .shadow(
                                10.dp,
                                RoundedCornerShape(10.dp),
                                true,
                                DefaultShadowColor,
                                DefaultShadowColor
                            )
                            .clip(RoundedCornerShape(10.dp))
                            .border(0.1.dp, Color.Gray, RoundedCornerShape(10.dp))
                    )
                }
                TextField(
                    modifier = Modifier
                        .width(180.dp)
                        .height(60.dp),
                    value = exerciseTitle,
                    onValueChange = { exerciseTitle = it },
                    label = { Text("Title") }
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                .fillMaxSize(),

            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .width(280.dp)
                        .height(60.dp),
                    value = numberOfSets,
                    onValueChange = {
                        numberOfSets = it },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    label = {  }
                )
                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(
                    modifier = Modifier
                        .width(280.dp)
                        .height(60.dp),
                    value = numberOfReps,
                    onValueChange = { numberOfReps = it },
                    label = { Text("Number of reps") }
                )
                Spacer(modifier = Modifier.height(20.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .width(280.dp)
                        .height(60.dp),
                    value = weight,
                    onValueChange = { weight = it },
                    label = { Text("Weight") }
                )
                Spacer(modifier = Modifier.height(20.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .width(280.dp)
                        .height(60.dp),
                    value = timeInMinutes,
                    onValueChange = { timeInMinutes = it },
                    label = { Text("Time in minutes") }
                )
                Spacer(modifier = Modifier.height(20.dp))
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier
                ) {
                    Text(text = "Drop-set")
                    Spacer(modifier = Modifier.width(10.dp))
                    Icon(imageVector = Icons.Outlined.Info,
                        contentDescription = "Drop-set information",
                        modifier = Modifier.size(20.dp))
                    Checkbox(
                        checked = isDropset,
                        onCheckedChange = { isChecked -> isDropset = isChecked }
                    )
                }
                Spacer(modifier = Modifier.height(30.dp))
                Button(onClick = {
                    // Convert string values to integers safely
                    val setsInt = numberOfSets.toIntOrNull() ?: 0
                    val repsInt = numberOfReps.toIntOrNull() ?: 0
                    val weightInt = weight.toIntOrNull() ?: 0
                    val durationInt = timeInMinutes.toIntOrNull() ?: 0

                    // Create and insert the exercise using the ViewModel
                    if (exerciseTitle.isNotEmpty()) {
                        exercisesViewModel.insertExercise(
                            Exercise(
                                name = exerciseTitle,
                                sets = setsInt,
                                repetitions = repsInt,
                                weight = weightInt,
                                duration = durationInt,
                                dropSet = isDropset
                            )
                        )
                        navController.popBackStack()
                    }
                }
                ){
                    Text(text = "Save")
                }
                Button(
                    onClick = { },
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = MaterialTheme.colorScheme.errorContainer),
                ) {
                    Text(text = "Cancel", color = MaterialTheme.colorScheme.onTertiaryContainer)
                }
            }
        }
    }
}

private fun insertExercise(
    title: String,
    sets: Int,
    reps: Int,
    weight: Int,
    isDropset: Boolean,
    duration: Int
){
    if (title.isNotEmpty() && !sets.equals(null) && !reps.equals(null) && !duration.equals(null)){
        val exercise = Exercise(
            name = title,
            sets = sets,
            repetitions = reps,
            weight = weight,
            duration = duration,
            dropSet = isDropset
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAddNewExerciseScreen() {
    val navController = rememberNavController()
    Assignemnt_fitTheme {
        AddNewExerciseScreen(navController = navController)
    }
}
