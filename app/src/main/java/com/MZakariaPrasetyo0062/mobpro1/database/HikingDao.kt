package com.MZakariaPrasetyo0062.mobpro1.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.MZakariaPrasetyo0062.mobpro1.model.HikingPlan
import kotlinx.coroutines.flow.Flow

@Dao
interface HikingDao {
    @Insert
    suspend fun insert(plan: HikingPlan)

    @Update
    suspend fun update(plan: HikingPlan)

    @Query("SELECT * FROM hiking_plan ORDER BY mountainName ASC")
    fun getAllPlans(): Flow<List<HikingPlan>>

    @Query("SELECT * FROM hiking_plan WHERE id = :id")
    suspend fun getPlanById(id: Long): HikingPlan?

    @Query("DELETE FROM hiking_plan WHERE id = :id")
    suspend fun deleteById(id: Long)
}