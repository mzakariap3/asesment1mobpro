package com.MZakariaPrasetyo0062.mobpro1.ui.screen

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalResources
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.MZakariaPrasetyo0062.mobpro1.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController, onNavigateToAbout: () -> Unit) {
    val context = LocalContext.current
    val resources = LocalResources.current

    var weight by rememberSaveable { mutableStateOf("") }
    var isHardTrack by rememberSaveable { mutableStateOf(false) }
    var resultText by rememberSaveable { mutableStateOf("") }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.main_title)) },
                actions = {
                    IconButton(onClick = onNavigateToAbout) {
                        Icon(Icons.Default.Info, contentDescription = "About")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.mountain_img),
                contentDescription = "Logo",
                modifier = Modifier.size(180.dp).padding(vertical = 16.dp)
            )

            Text(
                text = stringResource(R.string.calorie_estimation),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = weight,
                onValueChange = { weight = it },
                label = { Text(stringResource(R.string.pack_weight)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            ) {
                RadioButton(selected = !isHardTrack, onClick = { isHardTrack = false })
                Text("Normal")
                Spacer(modifier = Modifier.width(16.dp))
                RadioButton(selected = isHardTrack, onClick = { isHardTrack = true })
                Text("Hard")
            }

            Button(
                onClick = {
                    if (weight.isEmpty()) {
                        Toast.makeText(context, context.resources.getString(R.string.input_weight_error), Toast.LENGTH_SHORT).show()
                    } else {
                        val w = weight.toFloatOrNull() ?: 0f
                        val calories = w * if (isHardTrack) 150 else 80

                        resultText = resources.getString(
                            R.string.result_calories,
                            weight,
                            calories.toInt()
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.calculate_calories))
            }

            if (resultText.isNotEmpty()) {
                Spacer(modifier = Modifier.height(16.dp))
                Card(
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = resultText, style = MaterialTheme.typography.bodyLarge)
                        Button(
                            onClick = { shareResult(context, resultText) },
                            modifier = Modifier.padding(top = 8.dp)
                        ) {
                            Text(stringResource(R.string.share))
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { navController.navigate("list_plan") },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
            ) {
                Icon(Icons.Default.List, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text(stringResource(R.string.view_plans))
            }
        }
    }
}

private fun shareResult(context: Context, text: String) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, text)
    }
    context.startActivity(Intent.createChooser(intent, "Share via"))
}