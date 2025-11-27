package com.safetrace.safetraceapp.data.repository

import com.safetrace.safetraceapp.data.db.UserDao
import com.safetrace.safetraceapp.data.model.User
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {
    val user: Flow<User?> = userDao.getUser()

    suspend fun insertUser(user: User) {
        userDao.insertUser(user)
    }

    suspend fun isUserRegistered(): Boolean {
        return userDao.getUserCount() > 0
    }

    suspend fun getEmergencyContactNumber(): String? {
        return userDao.getEmergencyContactNumber()
    }
}
