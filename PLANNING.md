# Planification de l'application SafeTrace

## 1. Architecture et Technologies
*   **Langage :** Kotlin
*   **Architecture :** MVVM (Model-View-ViewModel)
*   **Base de données locale :** Room
*   **Caméra :** CameraX
*   **Localisation :** FusedLocationProvider
*   **SMS :** SmsManager
*   **Déclencheur SOS :** BroadcastReceiver + Service en arrière-plan
*   **Interface utilisateur :** XML + Material Design 3

## 2. Modèles de Données (Room Entities)
1.  **`User` (Pour les données d'inscription et le contact d'urgence)**
    *   `id: Int` (Primary Key)
    *   `name: String`
    *   `firstName: String`
    *   `age: Int`
    *   `phoneNumber: String`
    *   `address: String`
    *   `emergencyContactNumber: String`
2.  **`JournalEntry` (Pour les entrées du journal)**
    *   `id: Int` (Primary Key)
    *   `timestamp: Long` (Date et heure automatiques)
    *   `text: String`
    *   `photoPath: String?` (Chemin local vers la photo)
    *   `videoPath: String?` (Chemin local vers la vidéo)

## 3. Composants Clés
*   **`MainActivity` :** Conteneur principal, gère la navigation (Navigation Component).
*   **`AuthActivity` :** Gère l'écran de mot de passe au lancement.
*   **`RegistrationFragment` :** Formulaire de première ouverture.
*   **`JournalFragment` :** Affichage et ajout d'entrées de journal.
*   **`CameraFragment` :** Utilisation de CameraX pour la capture.
*   **`HelpFragment` :** Liste des contacts utiles.
*   **`PositiveFragment` :** Page de citations et messages positifs.
*   **`SOSService` :** Service en arrière-plan pour écouter le BroadcastReceiver du bouton Power et gérer l'envoi du SMS.
*   **`UserRepository` / `JournalRepository` :** Couche d'abstraction pour l'accès aux données Room.
*   **`UserViewModel` / `JournalViewModel` :** ViewModels pour l'architecture MVVM.

## 4. Sécurité
*   **Mot de passe :** Stocké dans `SharedPreferences` (hashé si possible, mais pour la simplicité du prototype, une simple vérification sera mise en place).
*   **Données :** Stockage local uniquement (Room et fichiers internes).
*   **Preuves (Photos/Vidéos) :** Stockées dans un répertoire interne de l'application, non visible dans la galerie.

## 5. Fonction SOS (Détails)
1.  **`PowerButtonReceiver` (BroadcastReceiver) :** Écoute les événements du bouton Power.
2.  **`SOSService` (Service) :**
    *   Déclenché par le Receiver après 4 appuis.
    *   Utilise `FusedLocationProviderClient` pour obtenir la dernière localisation connue.
    *   Génère un lien Google Maps.
    *   Utilise `SmsManager` pour envoyer le SMS au contact d'urgence.
    *   Nécessite les permissions `ACCESS_FINE_LOCATION`, `SEND_SMS`, `RECEIVE_BOOT_COMPLETED` (pour redémarrer le service).

## 6. Pages (Fragments)
*   **`HomeFragment`**
*   **`JournalFragment`**
*   **`HelpFragment`**
*   **`PositiveFragment`**
*   **`SettingsFragment`**
*   **`RegistrationFragment`**
*   **`AuthFragment`** (Écran de connexion)
