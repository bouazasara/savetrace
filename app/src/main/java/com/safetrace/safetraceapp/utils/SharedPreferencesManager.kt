package com.safetrace.safetraceapp.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesManager(context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREFS_NAME = "SafeTracePrefs"
        private const val KEY_PASSWORD = "password"
        private const val KEY_EMERGENCY_CONTACT = "emergency_contact"
    }

    fun savePassword(password: String) {
        // NOTE: Dans une application réelle, le mot de passe devrait être hashé (ex: SHA-256)
        // et stocké de manière plus sécurisée (ex: Android Keystore).
        // Pour cet exercice, nous stockons le mot de passe en clair pour la simplicité de la vérification.
        prefs.edit().putString(KEY_PASSWORD, password).apply()
    }

    fun checkPassword(password: String): Boolean {
        return prefs.getString(KEY_PASSWORD, null) == password
    }

    fun saveEmergencyContact(number: String) {
        prefs.edit().putString(KEY_EMERGENCY_CONTACT, number).apply()
    }

    fun getEmergencyContact(): String? {
        return prefs.getString(KEY_EMERGENCY_CONTACT, null)
    }
}
