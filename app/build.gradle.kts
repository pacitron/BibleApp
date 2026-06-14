plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace  = "com.example.bibleapp"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.bibleapp"
        minSdk        = 26   // Android 8.0 — covers ~96% of active devices
        targetSdk     = 35
        versionCode   = 1
        versionName   = "1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true   // Enable Jetpack Compose
    }
}

dependencies {
    // ── YouVersion Platform SDK ────────────────────────────────────────
    implementation("com.youversion.platform:platform-core:1.6.0")
    implementation("com.youversion.platform:platform-ui:1.6.0")

    // ── Compose BOM ───────────────────────────────────────────────────
    val composeBom = platform("androidx.compose:compose-bom:2024.09.00")
    implementation(composeBom)

    // Core Compose UI rendering
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")

    // Material Design 3 components
    implementation("androidx.compose.material3:material3")
    implementation("com.google.android.material:material:1.12.0")

    // Extended icon set
    implementation("androidx.compose.material:material-icons-extended")

    // ── Navigation ────────────────────────────────────────────────────
    implementation("androidx.navigation:navigation-compose:2.8.5")

    // ── Android core ──────────────────────────────────────────────────
    implementation("androidx.activity:activity-compose:1.9.3")
    implementation("androidx.core:core-ktx:1.15.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")

    // ── Debug only ────────────────────────────────────────────────────
    debugImplementation("androidx.compose.ui:ui-tooling")
}
