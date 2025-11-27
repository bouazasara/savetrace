package com.safetrace.safetraceapp

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.safetrace.safetraceapp.data.repository.JournalRepository
import com.safetrace.safetraceapp.data.repository.UserRepository
import com.safetrace.safetraceapp.ui.auth.UserViewModel
import com.safetrace.safetraceapp.ui.journal.JournalViewModel

class ViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val app = application as SafeTraceApplication
        return when {
            modelClass.isAssignableFrom(UserViewModel::class.java) -> {
                UserViewModel(app.userRepository, application) as T
            }
            modelClass.isAssignableFrom(JournalViewModel::class.java) -> {
                JournalViewModel(app.journalRepository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
