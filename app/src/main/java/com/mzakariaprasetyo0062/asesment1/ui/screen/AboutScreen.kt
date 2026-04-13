package com.mzakariaprasetyo0062.asesment1.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mzakariaprasetyo0062.asesment1.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(onNavigateBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.about_title)) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back") // Poin 6: Up Button
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp)) {
            Text("Nama: M. Zakaria Prasetyo", style = MaterialTheme.typography.titleMedium)
            Text("NIM: [607062400062]")
            Text("Kelas: [D3IF 48-01]")
            Spacer(modifier = Modifier.height(16.dp))
            Text("Aplikasi ini dibuat untuk membantu pendaki menghitung kebutuhan kalori berdasarkan beban tas.")
        }
    }
}