package com.MZakariaPrasetyo0062.mobpro1.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.MZakariaPrasetyo0062.mobpro1.database.HikingDao
import com.MZakariaPrasetyo0062.mobpro1.model.HikingPlan
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class MainViewModel(dao: HikingDao) : ViewModel() {
    val data: StateFlow<List<HikingPlan>> = dao.getAllPlans().stateIn(
        scope = viewModelScope,
        started = SharingStarted.Companion.WhileSubscribed(5000),
        initialValue = emptyList()
    )
}