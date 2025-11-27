package com.safetrace.safetraceapp.utils

import android.content.Context
import java.io.File

object FileHelper {

    /**
     * Retourne le répertoire sécurisé pour le stockage des preuves (photos/vidéos).
     * Ce répertoire est interne à l'application et n'est pas visible dans la galerie.
     */
    fun getSecureMediaDir(context: Context): File {
        val mediaDir = File(context.filesDir, "safe_media")
        if (!mediaDir.exists()) {
            mediaDir.mkdirs()
        }
        // Créer un fichier .nomedia pour empêcher la galerie de scanner ce répertoire
        File(mediaDir, ".nomedia").createNewFile()
        return mediaDir
    }

    /**
     * Crée un fichier temporaire pour la capture de photo/vidéo.
     */
    fun createMediaFile(context: Context, isPhoto: Boolean): File {
        val extension = if (isPhoto) ".jpg" else ".mp4"
        val prefix = if (isPhoto) "IMG_" else "VID_"
        val fileName = prefix + System.currentTimeMillis() + extension
        return File(getSecureMediaDir(context), fileName)
    }
}
