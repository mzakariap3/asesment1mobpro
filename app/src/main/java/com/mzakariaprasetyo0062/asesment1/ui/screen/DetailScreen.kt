package com.mzakariaprasetyo0062.asesment1.ui.screen

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.mzakariaprasetyo0062.asesment1.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavHostController, suhu: String, lembap: String) {
    val context = LocalContext.current

    // Logika Analisis Sederhana
    val suhuAngka = suhu.toFloatOrNull() ?: 0f
    val status = when {
        suhuAngka < 10 -> "Bahaya: Resiko Hipotermia!"
        suhuAngka > 35 -> "Waspada: Cuaca Sangat Panas!"
        else -> "Kondisi Aman untuk Mendaki."
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Hasil Analisis") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Kembali")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        val shareText = "Hasil Analisis SafeHike:\nSuhu: $suhu°C\nKelembapan: $lembap\nStatus: $status"
                        val intent = Intent(Intent.ACTION_SEND).apply {
                            type = "text/plain"
                            putExtra(Intent.EXTRA_TEXT, shareText)
                        }
                        context.startActivity(Intent.createChooser(intent, "Bagikan via"))
                    }) {
                        Icon(Icons.Default.Share, contentDescription = "Share")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Gambar (Syarat Modul 03)
            // Pastikan kamu punya gambar bernama 'ic_hike' di folder res/drawable
            // Jika belum ada, ganti R.drawable.ic_hike dengan R.drawable.ic_launcher_foreground
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Status Image",
                modifier = Modifier.size(150.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(text = "Data Terdeteksi:", fontSize = 16.sp)
            Text(
                text = "$suhu°C | Kelembapan $lembap",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                colors = CardDefaults.cardColors(
                    containerColor = if (status.contains("Bahaya")) Color(0xFFFFEBEE) else Color(0xFFE8F5E9)
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = status,
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    color = if (status.contains("Bahaya")) Color.Red else Color(0xFF2E7D32)
                )
            }
        }
    }
}