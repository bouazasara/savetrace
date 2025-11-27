package com.safetrace.safetraceapp.utils

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log

/**
 * Classe pour détecter 4 appuis rapides sur le bouton Power (via les événements SCREEN_ON/OFF).
 * NOTE: L'écoute directe du bouton Power est très limitée sur Android pour des raisons de sécurité.
 * Cette implémentation est une simulation basée sur les événements d'écran.
 */
class PowerButtonPressDetector(private val context: Context, private val onFourPresses: () -> Unit) {

    private var pressCount = 0
    private val handler = Handler(Looper.getMainLooper())
    private val resetDelay = 1500L // Délai en ms pour réinitialiser le compteur

    private val resetRunnable = Runnable {
        pressCount = 0
        Log.d("PowerButtonDetector", "Compteur réinitialisé.")
    }

    fun handleScreenOff() {
        // L'appui sur le bouton Power éteint l'écran
        incrementCount()
    }

    fun handleScreenOn() {
        // L'appui sur le bouton Power allume l'écran
        incrementCount()
    }

    private fun incrementCount() {
        pressCount++
        Log.d("PowerButtonDetector", "Appui détecté. Compteur: $pressCount")

        // Annuler le reset précédent
        handler.removeCallbacks(resetRunnable)

        if (pressCount >= 4) {
            onFourPresses()
            pressCount = 0 // Réinitialiser immédiatement après le déclenchement
        } else {
            // Planifier la réinitialisation si aucun autre appui n'arrive
            handler.postDelayed(resetRunnable, resetDelay)
        }
    }
}
