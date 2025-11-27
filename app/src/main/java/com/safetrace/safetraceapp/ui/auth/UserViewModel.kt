package com.safetrace.safetraceapp.ui.auth

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.safetrace.safetraceapp.data.model.User
import com.safetrace.safetraceapp.data.repository.UserRepository
import com.safetrace.safetraceapp.utils.SharedPreferencesManager
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository, application: Application) : AndroidViewModel(application) {

    val currentUser: LiveData<User?> = repository.user.asLiveData()

    private val sharedPrefs = SharedPreferencesManager(application)

    fun registerUser(user: User, password: String) = viewModelScope.launch {
        repository.insertUser(user)
        sharedPrefs.savePassword(password)
    }

    fun checkPassword(password: String): Boolean {
        return sharedPrefs.checkPassword(password)
    }



    fun isUserRegistered(): LiveData<Boolean> = repository.user.map { it != null }.asLiveData()



    fun getEmergencyContactNumber(): String? {
        return sharedPrefs.getEmergencyContact()
    }

    fun saveEmergencyContact(number: String) {
        sharedPrefs.saveEmergencyContact(number)
    }

    fun savePassword(password: String) {
        sharedPrefs.savePassword(password)
    }
}
