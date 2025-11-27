package com.safetrace.safetraceapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_data")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val firstName: String,
    val age: Int,
    val phoneNumber: String,
    val address: String,
    val emergencyContactNumber: String
)
