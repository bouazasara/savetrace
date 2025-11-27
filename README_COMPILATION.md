# SafeTrace - Instructions de Compilation

## Prérequis

- Android Studio Hedgehog (2023.1.1) ou plus récent
- JDK 17 ou supérieur
- Android SDK avec API 34

## Étapes de compilation

### 1. Ouvrir le projet dans Android Studio

1. Lancez Android Studio
2. Cliquez sur "Open an Existing Project"
3. Naviguez vers le dossier `SafeTrace` et sélectionnez-le
4. Attendez que Gradle synchronise le projet

### 2. Synchroniser Gradle

- Android Studio devrait automatiquement détecter le projet et synchroniser Gradle
- Si ce n'est pas le cas, cliquez sur "File" > "Sync Project with Gradle Files"

### 3. Compiler le projet

#### Via Android Studio :
- Cliquez sur "Build" > "Make Project" (Ctrl+F9 / Cmd+F9)
- Ou cliquez sur "Build" > "Rebuild Project"

#### Via ligne de commande :
```bash
cd SafeTrace
./gradlew assembleDebug
```

### 4. Exécuter l'application

1. Connectez un appareil Android ou démarrez un émulateur
2. Cliquez sur le bouton "Run" (icône play verte) dans Android Studio
3. Sélectionnez votre appareil/émulateur

## Corrections apportées

### Fichiers Gradle
- ✅ Correction de `build.gradle.kts` (suppression de la ligne `true` erronée)
- ✅ Mise à jour de la version AGP de 8.4.0 à 8.2.2 (compatible avec Gradle 8.2)
- ✅ Remplacement de `kotlin-kapt` par `ksp` pour Room

### Code Kotlin
- ✅ Correction de `MainActivity.kt` : logique de navigation corrigée
- ✅ Ajout de la gestion des permissions runtime
- ✅ Correction du cycle de vie avec `lifecycleScope`

### Ressources
- ✅ Ajout de `addEntryFragment` dans `nav_graph.xml`
- ✅ Ajout de la permission `POST_NOTIFICATIONS` pour Android 13+
- ✅ Création des icônes PNG pour toutes les densités

### Navigation
- ✅ Correction de la logique de démarrage de l'application
- ✅ Le graph démarre sur `authFragment` par défaut
- ✅ Navigation vers `registrationFragment` si l'utilisateur n'est pas enregistré

## Structure du projet

```
SafeTrace/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/safetrace/safetraceapp/
│   │   │   │   ├── data/          # Base de données et modèles
│   │   │   │   ├── ui/            # Fragments et ViewModels
│   │   │   │   ├── service/       # Services SOS
│   │   │   │   ├── utils/         # Utilitaires
│   │   │   │   ├── MainActivity.kt
│   │   │   │   └── SafeTraceApplication.kt
│   │   │   ├── res/               # Ressources (layouts, drawables, etc.)
│   │   │   └── AndroidManifest.xml
│   ├── build.gradle.kts
├── build.gradle.kts
├── settings.gradle.kts
└── gradle/wrapper/
```

## Fonctionnalités

- ✅ Authentification par mot de passe
- ✅ Enregistrement utilisateur (première ouverture)
- ✅ Journal sécurisé avec photos/vidéos
- ✅ Contacts d'aide
- ✅ Page de contenu positif
- ✅ Service SOS (détection bouton power)
- ✅ Géolocalisation et envoi SMS d'urgence

## Problèmes connus et solutions

### Erreur "Unresolved reference"
- Assurez-vous que Gradle a terminé la synchronisation
- Nettoyez et reconstruisez : "Build" > "Clean Project" puis "Rebuild Project"

### Erreur de permissions
- Les permissions sont demandées au runtime dans `MainActivity`
- Testez sur un appareil réel ou un émulateur avec Google Play Services

### Problème de navigation
- Le graph de navigation est configuré avec `authFragment` comme destination de départ
- La logique vérifie si l'utilisateur est enregistré et navigue en conséquence

## Support

Pour toute question ou problème, consultez la documentation officielle d'Android :
- https://developer.android.com/studio
- https://developer.android.com/guide
