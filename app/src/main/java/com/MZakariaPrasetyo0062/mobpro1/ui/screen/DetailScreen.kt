package com.MZakariaPrasetyo0062.mobpro1.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.MZakariaPrasetyo0062.mobpro1.R
import com.MZakariaPrasetyo0062.mobpro1.util.ViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavHostController, id: Long? = null) {
    val context = LocalContext.current
    val factory = ViewModelFactory(context)
    val viewModel: DetailViewModel = viewModel(factory = factory)

    var namaGunung by rememberSaveable { mutableStateOf("") }
    var beratBeban by rememberSaveable { mutableStateOf("") }
    var levelKesulitan by rememberSaveable { mutableStateOf("Normal") }
    var butuhPorter by rememberSaveable { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }

    LaunchedEffect(id) {
        if (id != null) {
            val plan = viewModel.getPlanById(id)
            plan?.let {
                namaGunung = it.mountainName
                beratBeban = it.packWeight.toString()
                levelKesulitan = it.difficulty
                butuhPorter = it.needPorter
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (id == null) stringResource(R.string.add_plan) else stringResource(R.string.change_plan)) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    if (id != null) {
                        IconButton(onClick = { showDeleteDialog = true }) {
                            Icon(Icons.Default.Delete, contentDescription = "Delete")
                        }
                    }
                    IconButton(onClick = {
                        if (namaGunung.isEmpty() || beratBeban.isEmpty()) {
                            Toast.makeText(context, context.resources.getString(R.string.mustinputdata), Toast.LENGTH_SHORT).show()
                        } else {
                            val berat = beratBeban.toFloatOrNull() ?: 0f
                            if (id == null) {
                                viewModel.insert(namaGunung, levelKesulitan, berat, butuhPorter)
                            } else {
                                viewModel.update(id, namaGunung, levelKesulitan, berat, butuhPorter)
                            }
                            navController.popBackStack()
                        }
                    }) {
                        Icon(Icons.Default.Check, contentDescription = "Save")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = namaGunung,
                onValueChange = { namaGunung = it },
                label = { Text(stringResource(R.string.mountain_name_hint)) },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = beratBeban,
                onValueChange = { beratBeban = it },
                label = { Text(stringResource(R.string.pack_weight)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(stringResource(R.string.difficulty_label), style = MaterialTheme.typography.bodyLarge)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                RadioButton(
                    selected = (levelKesulitan == "Normal"),
                    onClick = { levelKesulitan = "Normal" }
                )
                Text("Normal")
                Spacer(modifier = Modifier.width(16.dp))
                RadioButton(
                    selected = (levelKesulitan == "Hard"),
                    onClick = { levelKesulitan = "Hard" }
                )
                Text("Hard")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.use_porter), style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.weight(1f))
                Switch(
                    checked = butuhPorter,
                    onCheckedChange = { butuhPorter = it }
                )
            }
        }
    }
    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text(stringResource(R.string.delete_plan)) },
            text = { Text(stringResource(R.string.delete_plan_info)+ " $namaGunung? " + stringResource(R.string.delete_plan_info2)) },
            confirmButton = {
                TextButton(
                    onClick = {
                        if (id != null) {
                            viewModel.delete(id)
                            showDeleteDialog = false
                            navController.popBackStack()
                        }
                    }
                ) {
                    Text(stringResource(R.string.delete), color = MaterialTheme.colorScheme.error)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = false }) {
                    Text(stringResource(R.string.cancel))
                }
            }
        )
    }
}