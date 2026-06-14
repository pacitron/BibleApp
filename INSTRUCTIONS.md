# YouVersion Bible App — Windows Setup & Run Guide

## What the app does

| Tab | What you see |
|-----|-------------|
| **Today** | The official Verse of the Day from YouVersion, updated daily |
| **Bible** | A full Bible reader — pick any version, book, and chapter |

The Bible tab features:
- **Version selector** — BSB, KJV, ESV, NIV, NLT (plus more once licensed)
- **Book selector** — all 66 books, Old and New Testament
- **Chapter selector** — matches the correct chapter count per book
- **← Prev / Next →** — flip through chapters (auto-advances across books)
- **Auto-scroll to top** when you navigate to a new passage

---

## Step 1 — Get your free App Key (5 minutes)

The YouVersion SDK needs an App Key to fetch Bible content.

1. Go to **https://platform.youversion.com** and sign up for a free account
2. Click **Create App** (give it any name, e.g. *My Bible App*)
3. Copy the **App Key** shown on the app detail page — it looks like a long string of letters and numbers

---

## Step 2 — Install Android Studio (15–30 minutes)

Android Studio is the official IDE for Android; it includes the Java SDK, Android SDK, and an emulator.

1. Download Android Studio from **https://developer.android.com/studio**
   (choose the Windows `.exe` installer)
2. Run the installer — accept all defaults
3. On first launch, the **Setup Wizard** appears:
   - Accept the license agreements
   - Choose **Standard** install type
   - Let it download the Android SDK components (this takes a few minutes)
4. When you see the **Welcome to Android Studio** screen, setup is complete

---

## Step 3 — Add your App Key to the project

Open the file:
```
BibleApp\app\src\main\java\com\example\bibleapp\BibleApp.kt
```

Find this line:
```kotlin
appKey  = "YOUR_APP_KEY_HERE"
```

Replace `YOUR_APP_KEY_HERE` with the key you copied in Step 1. Example:
```kotlin
appKey  = "abc123xyz..."
```

Save the file.

---

## Step 4 — Open the project in Android Studio

1. Open **Android Studio**
2. On the welcome screen click **Open**
   (or go to **File → Open** if you already have a project open)
3. Browse to the **BibleApp** folder (the one containing `settings.gradle.kts`) and click **OK**
4. Android Studio will start syncing. Watch the progress bar at the bottom.
   - First sync downloads Gradle (~200 MB) and all libraries — allow 5–10 minutes
   - If prompted *"Trust this project?"* click **Trust Project**
   - If prompted to update the Android Gradle Plugin, click **Update**
5. Wait until the bottom status bar says **"Gradle sync finished"** with no red errors

---

## Step 5 — Create an Android Emulator

You only need to do this once.

1. In Android Studio, click **Tools → Device Manager** (or the phone icon in the toolbar)
2. Click **+ Create Virtual Device**
3. Select **Phone → Pixel 7** (or any phone) and click **Next**
4. On the *System Image* screen:
   - Choose **Tiramisu (API 33)** or newer
   - If there's a **Download** link next to it, click it and wait for it to finish
   - Click **Next**
5. Leave all settings at defaults and click **Finish**
6. The new emulator appears in the Device Manager list — you can close that panel

---

## Step 6 — Run the app

1. In the toolbar at the top of Android Studio, confirm the device dropdown shows your emulator (e.g. **Pixel 7 API 33**)
2. Click the **green ▶ Run** button (or press **Shift + F10**)
3. The emulator boots (first boot takes ~2 minutes)
4. The **Bible App** installs and launches automatically

---

## How to test each feature

### Verse of the Day
- Open the app → you land on the **Today** tab
- You should see today's date and a verse rendered by the YouVersion component
- The verse changes every day automatically

### Bible Reader
1. Tap the **Bible** tab at the bottom
2. The app opens at **Genesis 1** in **BSB** by default
3. Tap the **BSB** button (left) → pick a different translation
4. Tap the **Genesis** button (middle) → scroll through all 66 books, tap one
5. Tap the **1** button (right) → pick a chapter number
6. Use **← Prev** and **Next →** to flip chapters
   - Tapping Next → on the last chapter of a book moves to the first chapter of the next book
   - Tapping ← Prev on chapter 1 of a book moves to the last chapter of the previous book
7. Scroll up and down inside the chapter text

---

## Troubleshooting

| Problem | Fix |
|---------|-----|
| Gradle sync fails | Make sure you're connected to the internet; Android Studio needs to download dependencies on first sync |
| Bible text area is blank / shows error | Your App Key is missing or wrong — recheck `BibleApp.kt` |
| "Version shows no content" | That translation may need a license accepted at platform.youversion.com |
| Emulator is very slow | Normal on first boot. Wait 2–3 minutes after the emulator finishes loading before tapping Run |
| Build error about Kotlin version | Open **File → Project Structure → SDK Location** and confirm a recent Android SDK is installed |

---

## About Audio Bible

The current YouVersion Kotlin SDK (`platform-core`) focuses on text content
(`BibleText`, `BibleCard`, `VerseOfTheDay`).  Audio Bible playback is not
exposed through the SDK yet.  Check **https://developers.youversion.com** for
future SDK updates — audio may be added in a later release.

---

## Project file map

```
BibleApp/
├── settings.gradle.kts          Root project settings
├── build.gradle.kts             Plugin version declarations
├── gradle.properties            JVM / AndroidX flags
├── gradle/wrapper/
│   └── gradle-wrapper.properties   Pins Gradle 8.7
└── app/
    ├── build.gradle.kts         All dependencies (SDK, Compose, Navigation)
    └── src/main/
        ├── AndroidManifest.xml  App entry point + INTERNET permission
        └── java/com/example/bibleapp/
            ├── BibleApp.kt          SDK initialisation  ← PUT YOUR KEY HERE
            ├── MainActivity.kt      Bottom navigation host
            ├── data/
            │   └── BibleData.kt     All 66 books + 5 versions
            └── ui/screens/
                ├── VotdScreen.kt    Verse of the Day tab
                └── ReaderScreen.kt  Bible reader tab + DropdownSelector
```
