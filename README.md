# BibleApp

A modern Android Bible application built with Jetpack Compose and the YouVersion Platform SDK.

## Features
- **Verse of the Day**: Get inspired daily with a featured verse.
- **Bible Reader**: Read the full Bible with support for multiple versions and easy navigation.
- **Modern UI**: Built entirely with Material 3 and Jetpack Compose.

## Prerequisites
- Android Studio Ladybug (or newer)
- Android SDK 36
- A YouVersion Platform App Key

## Setup Instructions

### 1. Get a YouVersion App Key
To display Bible content, you must register for a free developer account:
1. Go to [https://platform.youversion.com](https://platform.youversion.com).
2. Sign up and create a new project to generate your **App Key**.

### 2. Configure the App Key
Once you have your key, you need to add it to the source code:
1. Open `app/src/main/java/com/example/bibleapp/BibleApp.kt`.
2. Find the `YouVersionPlatformConfiguration.configure` call inside `onCreate`.
3. Replace (the placeholder/sample key) with your real **App Key**.

```kotlin
// BibleApp.kt
YouVersionPlatformConfiguration.configure(
    this,
    "YOUR_ACTUAL_APP_KEY_HERE"
)
```

### 3. Emulation / Running the App
- Ensure **Virtualization (SVM/VT-x)** is enabled in your computer's BIOS to run the Android Emulator.
- If you encounter hypervisor errors, enable **Windows Hypervisor Platform** in Windows Features or use a physical device.

## Technologies Used
- **Kotlin**: Primary programming language.
- **Jetpack Compose**: UI Toolkit.
- **YouVersion Platform SDK**: Bible data and rendering.
- **Material 3**: Design system.
- **Navigation Compose**: App navigation.
