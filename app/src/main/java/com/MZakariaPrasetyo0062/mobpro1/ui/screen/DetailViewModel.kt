package com.MZakariaPrasetyo0062.mobpro1.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.MZakariaPrasetyo0062.mobpro1.database.HikingDao
import com.MZakariaPrasetyo0062.mobpro1.model.HikingPlan
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(private val dao: HikingDao) : ViewModel() {

    fun insert(mountainName: String, difficulty: String, weight: Float, porter: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            val plan = HikingPlan(
                mountainName = mountainName,
                difficulty = difficulty,
                packWeight = weight,
                needPorter = porter
            )
            dao.insert(plan)
        }
    }

    fun update(id: Long, mountainName: String, difficulty: String, weight: Float, porter: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            val plan = HikingPlan(
                id = id,
                mountainName = mountainName,
                difficulty = difficulty,
                packWeight = weight,
                needPorter = porter
            )
            dao.update(plan)
        }
    }
    suspend fun getPlanById(id: Long): HikingPlan? {
        return dao.getPlanById(id)
    }

    fun delete(id: Long) {
        viewModelScope.launch(Dispatchers.IO) { dao.deleteById(id)
        }
    }
}