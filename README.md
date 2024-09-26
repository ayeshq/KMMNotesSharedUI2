# Notes KMM App

## Abstract
A simple Kotlin multiplatform application, for taking notes.
The app has only two screens, the first for shwoing a list of cards for the stored notes, and a simple notes editor screen for creating/editing notes.

## Developed using Kotlin multi platform for Android/iOS with shared UI using Compose-UI

## Requirements for building
1. Android Studio Koala Feature Drop | 2024.1.2 Patch 1
2. Android api 34
4. Android Studio Kotlin Multiplatform plugin 0.8.3(241)-9
5. Gradle version: 8.2.2
3. Xcode Version 15.3 (15E204a)

## Building and testing the app
Open the project on Android studio, select Android or iOS targets, and click run.
Please make sure iOS sdk is downloaded by xCode to run on iOS simulator.
You might need to select which iOS simiulator in the run-configuration for iOS target.

## Dependencies
1. Compose:
   UI, Navigation, ViewModels
2. Koin dependency injection:
  koin-compose, koin-compose-viewmodel, and platform specific Koin-Android for managing context injection
3. SqlDelight
4. Kotlin Coroutines

## Architecture Overview
MVVM architecture. There are two view models, one for each screen: `NotesListViewModel` and `EditNoteViewModel`
One repository for managing notes crud operations: `NotesRepository`, which is provided by Koin for both view models via constructor injection.
And the `DatabaseDriverFactory` is also provided by koin to the `NotesRepository` via constructor injection as well.
