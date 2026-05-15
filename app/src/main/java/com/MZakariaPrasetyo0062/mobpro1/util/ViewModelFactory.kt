package com.MZakariaPrasetyo0062.mobpro1.util

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.MZakariaPrasetyo0062.mobpro1.database.HikingDb
import com.MZakariaPrasetyo0062.mobpro1.ui.screen.DetailViewModel
import com.MZakariaPrasetyo0062.mobpro1.ui.screen.MainViewModel

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val dao = HikingDb.getInstance(context).dao

        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(dao) as T
        } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}