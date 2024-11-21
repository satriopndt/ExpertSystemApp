package com.satriopndt.expertsystem.di

import android.content.Context
import com.satriopndt.expertsystem.data.preference.UserPreferences
import com.satriopndt.expertsystem.data.preference.dataStore
import com.satriopndt.expertsystem.data.repository.SystemRepository

object Injection {
    fun provideRepository(context: Context): SystemRepository {
        val pref = UserPreferences.getInstance(context.dataStore)
        return SystemRepository.getInstance(pref)
    }
}