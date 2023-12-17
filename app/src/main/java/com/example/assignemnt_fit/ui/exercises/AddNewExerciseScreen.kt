
import android.graphics.drawable.Icon
import android.widget.GridLayout
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.assignemnt_fit.ui.components.SubpageScaffold
import com.example.assignemnt_fit.ui.theme.Assignemnt_fitTheme
import com.example.assignemnt_fit.R
import org.checkerframework.checker.units.qual.C


@Composable
fun AddNewExerciseScreen() {
    SubpageScaffold(navController = rememberNavController()
    ) {
        Column {
            val modifier = Modifier
            var exerciseTitle by remember { mutableStateOf("") }
            var numberOfSets by remember { mutableStateOf("") }
            var numberOfReps by remember { mutableStateOf("") }
            var weight by remember { mutableStateOf("") }
            var timeInMinutes by remember { mutableStateOf("") }
            val checked = remember { mutableStateOf(false) }

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
                var text by remember { mutableStateOf("") }
                TextField(
                    modifier = Modifier
                        .width(180.dp)
                        .height(60.dp),
                    value = exerciseTitle,
                    onValueChange = { exerciseTitle = it },
                    label = { Text("Title") }
                )
            }
            Box(modifier = Modifier
                .fillMaxSize(),
                contentAlignment = Alignment.TopCenter) {
                Column {
                    OutlinedTextField(
                        modifier = Modifier
                            .width(280.dp)
                            .height(60.dp),
                        value = numberOfSets,
                        onValueChange = { numberOfSets = it },
                        label = { Text("Number of sets") }
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
                        Icon(imageVector = Icons.Filled.Info,
                            contentDescription = "Drop-set information",
                            modifier = Modifier.size(20.dp))
                        Checkbox(
                            checked = checked.value,
                            onCheckedChange = { isChecked -> checked.value = isChecked }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAddNewExerciseScreen() {
    Assignemnt_fitTheme {
        AddNewExerciseScreen()
    }
}
