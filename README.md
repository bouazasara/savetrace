
# savetrace
# SafeTrace - Application Android Sécurisée pour les Victimes de Violences

## Description du Projet

SafeTrace est une application Android moderne, élégante et discrète, conçue pour les femmes et personnes victimes de violences conjugales. Elle permet de documenter les événements de manière sécurisée et locale, d'accéder à des ressources d'aide, et de déclencher un SOS d'urgence discret.

## Technologies Utilisées

- **Langage :** Kotlin

- **Architecture :** MVVM (Model-View-ViewModel)

- **Base de données locale :** Room

- **Caméra :** CameraX (simulé dans le prototype)

- **Localisation :** FusedLocationProviderClient

- **SMS :** SmsManager

- **Interface utilisateur :** XML + Material Design 3

## Fonctionnalités Implémentées

1. **Sécurité et Authentification :**
  - Accès protégé par mot de passe (stocké via SharedPreferences).
  - Formulaire d'inscription initial pour la première ouverture.

1. **Journal Sécurisé :**
  - Enregistrement d'entrées datées automatiquement.
  - Possibilité d'ajouter des preuves (photos/vidéos) stockées dans un répertoire interne (`.nomedia`).

1. **Page d'Aide :**
  - Liste de contacts utiles (associations, urgences).
  - Fonctionnalité d'appel direct.

1. **Page Positive :**
  - Contenu motivant et apaisant (citations, messages).

1. **Fonction SOS d'Urgence :**
  - Déclenchement simulé par 4 appuis rapides sur le bouton Power (via `BroadcastReceiver` sur les événements `SCREEN_ON`/`SCREEN_OFF`).
  - Envoi automatique d'un SMS d'urgence avec lien Google Maps de la position actuelle.
  - Fonctionne en arrière-plan (`Foreground Service`).

## Structure du Projet

```kotlin


## Instructions pour l'Ouverture dans Android Studio

1. Ouvrez Android Studio.

1. Sélectionnez **Open an existing Android Studio project**.

1. Naviguez jusqu'au répertoire `/home/ubuntu/SafeTrace` et sélectionnez-le.

1. Android Studio devrait synchroniser le projet et télécharger les dépendances Gradle.

1. **Note Importante :** Le projet contient des TODOs pour l'implémentation complète de CameraX. La logique de sécurité et de SOS est en place, mais l'écoute du bouton Power est une simulation basée sur les événements d'écran, car l'accès direct est restreint par Android.

## Prochaines Étapes (TODOs)

- Implémenter la logique complète de CameraX dans `CameraFragment`.

- Ajouter la boîte de dialogue pour le changement de mot de passe dans `SettingsFragment`.

- Implémenter la fonction d'exportation des données dans `SettingsFragment`.

- Finaliser la gestion des contacts personnalisés dans `HelpFragment`.

- Ajouter des images motivantes à `PositiveFragment`.
