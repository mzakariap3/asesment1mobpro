package com.MZakariaPrasetyo0062.mobpro1.model

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "hiking_plan")
data class HikingPlan(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val mountainName: String,
    val difficulty: String,
    val packWeight: Float,
    val needPorter: Boolean
)