package com.safetrace.safetraceapp.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.telephony.SmsManager
import android.util.Log
import androidx.core.app.NotificationCompat
import com.safetrace.safetraceapp.MainActivity
import com.safetrace.safetraceapp.R
import com.safetrace.safetraceapp.SafeTraceApplication
import com.safetrace.safetraceapp.utils.LocationHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SOSService : Service() {

    private val serviceScope = CoroutineScope(Dispatchers.IO)
    private val CHANNEL_ID = "SOS_CHANNEL"
    private val NOTIFICATION_ID = 101

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        startForeground(NOTIFICATION_ID, buildNotification())
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent?.action == "ACTION_SEND_SOS") {
            sendSOS()
        }
        return START_STICKY
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                "SafeTrace SOS Service Channel",
                NotificationManager.IMPORTANCE_LOW
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(serviceChannel)
        }
    }

    private fun buildNotification() =
        NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(getString(R.string.sos_notification_title))
            .setContentText(getString(R.string.sos_notification_text))
            .setSmallIcon(R.drawable.ic_positive) // Utiliser une icône discrète
            .setContentIntent(getPendingIntent())
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .build()

    private fun getPendingIntent(): PendingIntent {
        val notificationIntent = Intent(this, MainActivity::class.java)
        return PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE)
    }

    private fun sendSOS() {
        serviceScope.launch {
            val userRepository = (application as SafeTraceApplication).userRepository
            val emergencyContact = userRepository.getEmergencyContactNumber()

            if (emergencyContact.isNullOrEmpty()) {
                Log.e("SOSService", "Numéro de contact d'urgence non configuré.")
                return@launch
            }

            val locationHelper = LocationHelper(applicationContext)
            val location = locationHelper.getLastLocation()

            val message: String
            if (location != null) {
                val mapLink = LocationHelper.getGoogleMapsLink(location.latitude, location.longitude)
                message = getString(R.string.sos_message, mapLink)
            } else {
                message = "Aide-moi, je suis en danger. Impossible d'obtenir ma position GPS."
            }

            try {
                val smsManager = SmsManager.getDefault()
                smsManager.sendTextMessage(emergencyContact, null, message, null, null)
                Log.i("SOSService", "SMS d'urgence envoyé à $emergencyContact: $message")
            } catch (e: Exception) {
                Log.e("SOSService", "Échec de l'envoi du SMS: ${e.message}")
            }
        }
    }
}
