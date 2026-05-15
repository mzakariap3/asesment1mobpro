package com.MZakariaPrasetyo0062.mobpro1.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import com.MZakariaPrasetyo0062.mobpro1.R
import com.MZakariaPrasetyo0062.mobpro1.model.HikingPlan
import com.MZakariaPrasetyo0062.mobpro1.util.ViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListPlanScreen(navController: NavHostController) {
    val context = LocalContext.current
    val factory = ViewModelFactory(context)
    val viewModel: MainViewModel = viewModel(factory = factory)
    val plans by viewModel.data.collectAsState()

    var showList by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.list_title)) },
                navigationIcon = {
                    IconButton(onClick = {navController.popBackStack() }) {
                        Icon (
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Kembali ke Menu Utama"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {showList = !showList}) {
                        Icon(
                            painter = painterResource(
                                if (showList) R.drawable.baseline_grid_view_24
                                else R.drawable.baseline_view_list_24
                        ),
                        contentDescription = stringResource(
                            if (showList) R.string.grid else R.string.list
                        ),
                        tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("detail") }) {
                Icon(Icons.Default.Add, contentDescription = "Tambah")
            }
        }
    ) { padding ->
        if (plans.isEmpty()) {
            EmptyStateDisplay(Modifier.padding(padding))
        } else {
            if(showList) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(plans) { plan ->
                    HikingPlanItem(plan = plan) {
                        navController.navigate("detail?id=${plan.id}")
                    }
                }
            }
        }else {
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize().padding(padding).padding(8.dp),
                    verticalItemSpacing = 8.dp,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(plans) { plan ->
                        GridItem(plan = plan) {
                            navController.navigate("detail?id=${plan.id}")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun GridItem(plan: HikingPlan, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable { onClick() },
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = plan.mountainName,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "Beban: ${plan.packWeight}kg",
                style = MaterialTheme.typography.bodySmall,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "Difficulty: ${plan.difficulty}",
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

@Composable
fun EmptyStateDisplay(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ) {
        Text(stringResource(R.string.empty_plan), style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
fun HikingPlanItem(plan: com.MZakariaPrasetyo0062.mobpro1.model.HikingPlan, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = plan.mountainName, style = MaterialTheme.typography.titleMedium)
            Text(
                text = "Level: ${plan.difficulty} | Beban: ${plan.packWeight}kg",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}