package com.safetrace.safetraceapp.ui.journal

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.safetrace.safetraceapp.data.model.JournalEntry
import com.safetrace.safetraceapp.data.repository.JournalRepository
import kotlinx.coroutines.launch

class JournalViewModel(private val repository: JournalRepository) : ViewModel() {

    val allEntries: LiveData<List<JournalEntry>> = repository.allEntries.asLiveData()

    fun insertEntry(entry: JournalEntry) = viewModelScope.launch {
        repository.insertEntry(entry)
    }
}
