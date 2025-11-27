package com.safetrace.safetraceapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.safetrace.safetraceapp.data.model.JournalEntry
import com.safetrace.safetraceapp.data.model.User

@Database(entities = [User::class, JournalEntry::class], version = 1, exportSchema = false)
abstract class SafeTraceDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun journalDao(): JournalDao
}
