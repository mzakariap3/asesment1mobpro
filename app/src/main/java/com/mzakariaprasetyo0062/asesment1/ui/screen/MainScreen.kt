package com.mzakariaprasetyo0062.asesment1.ui.screen

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
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.mzakariaprasetyo0062.asesment1.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(onNavigateToAbout: () -> Unit) {
    val context = LocalContext.current
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
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.mountain_bg),
                contentDescription = null,
                modifier = Modifier.size(180.dp)
            )

            OutlinedTextField(
                value = weight,
                onValueChange = { weight = it },
                label = { Text(stringResource(R.string.weight_label)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(stringResource(R.string.difficulty_label))
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(selected = !isHardTrack, onClick = { isHardTrack = false })
                Text("Easy")
                Spacer(modifier = Modifier.width(16.dp))
                RadioButton(selected = isHardTrack, onClick = { isHardTrack = true })
                Text("Hard")
            }

            Button(
                onClick = {
                    if (weight.isEmpty()) {
                        Toast.makeText(context, context.getString(R.string.error_weight), Toast.LENGTH_SHORT).show()
                    } else {
                        val calories = weight.toInt() * if (isHardTrack) 100 else 50
                        resultText = context.getString(R.string.result_msg, weight, calories.toString())
                    }
                },
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
            ) {
                Text(stringResource(R.string.calculate))
            }

            if (resultText.isNotEmpty()) {
                Text(resultText, modifier = Modifier.padding(top = 16.dp))
                Button(onClick = { shareResult(context, resultText) }) {
                    Text(stringResource(R.string.share))
                }
            }
        }
    }
}

private fun shareResult(context: Context, message: String) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, message)
    }
    context.startActivity(Intent.createChooser(intent, null))
}