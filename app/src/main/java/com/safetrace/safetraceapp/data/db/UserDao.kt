package com.safetrace.safetraceapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.safetrace.safetraceapp.data.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM user_data LIMIT 1")
    fun getUser(): Flow<User?>

    @Query("SELECT emergencyContactNumber FROM user_data LIMIT 1")
    suspend fun getEmergencyContactNumber(): String?

    @Query("SELECT COUNT(*) FROM user_data")
    suspend fun getUserCount(): Int
}
