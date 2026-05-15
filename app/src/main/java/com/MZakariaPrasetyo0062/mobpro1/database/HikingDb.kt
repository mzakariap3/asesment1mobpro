package com.MZakariaPrasetyo0062.mobpro1.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.MZakariaPrasetyo0062.mobpro1.model.HikingPlan

@Database(entities = [HikingPlan::class], version = 1, exportSchema = false)
abstract class HikingDb : RoomDatabase() {
    abstract val dao: HikingDao

    companion object {
        @Volatile
        private var INSTANCE: HikingDb? = null

        fun getInstance(context: Context): HikingDb {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HikingDb::class.java,
                    "hiking.db"
                ).build()


                INSTANCE = instance
                instance
            }
        }
    }
}