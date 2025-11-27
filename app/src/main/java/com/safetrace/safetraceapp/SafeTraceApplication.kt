package com.safetrace.safetraceapp

import android.app.Application
import androidx.room.Room
import com.safetrace.safetraceapp.data.db.SafeTraceDatabase
import com.safetrace.safetraceapp.data.repository.JournalRepository
import com.safetrace.safetraceapp.data.repository.UserRepository

class SafeTraceApplication : Application() {

    // Utilisation d'un pattern Singleton simple pour la base de données et les repositories
    private val database by lazy {
        Room.databaseBuilder(
            applicationContext,
            SafeTraceDatabase::class.java,
            "safetrace_db"
        ).build()
    }

    val userRepository by lazy {
        UserRepository(database.userDao())
    }

    val journalRepository by lazy {
        JournalRepository(database.journalDao())
    }
}
