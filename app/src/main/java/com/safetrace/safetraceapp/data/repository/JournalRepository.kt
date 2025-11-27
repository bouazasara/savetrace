package com.safetrace.safetraceapp.data.repository

import com.safetrace.safetraceapp.data.db.JournalDao
import com.safetrace.safetraceapp.data.model.JournalEntry
import kotlinx.coroutines.flow.Flow

class JournalRepository(private val journalDao: JournalDao) {
    val allEntries: Flow<List<JournalEntry>> = journalDao.getAllEntries()

    suspend fun insertEntry(entry: JournalEntry) {
        journalDao.insertEntry(entry)
    }
}
