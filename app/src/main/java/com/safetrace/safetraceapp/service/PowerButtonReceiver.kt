package com.safetrace.safetraceapp.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.safetrace.safetraceapp.utils.PowerButtonPressDetector

class PowerButtonReceiver : BroadcastReceiver() {

    // Utiliser un objet singleton pour le détecteur de pression
    companion object {
        private var detector: PowerButtonPressDetector? = null
    }

    override fun onReceive(context: Context, intent: Intent) {
        if (detector == null) {
            detector = PowerButtonPressDetector(context) {
                // Callback déclenché après 4 appuis rapides
                Log.d("PowerButtonReceiver", "4 appuis détectés. Déclenchement du SOS.")
                val sosIntent = Intent(context, SOSService::class.java).apply {
                    action = "ACTION_SEND_SOS"
                }
                context.startService(sosIntent)
            }
        }

        when (intent.action) {
            Intent.ACTION_SCREEN_OFF -> {
                detector?.handleScreenOff()
            }
            Intent.ACTION_SCREEN_ON -> {
                detector?.handleScreenOn()
            }
            Intent.ACTION_BOOT_COMPLETED -> {
                // Redémarrer le service au démarrage pour maintenir l'écoute
                val serviceIntent = Intent(context, SOSService::class.java)
                context.startService(serviceIntent)
            }
        }
    }
}
