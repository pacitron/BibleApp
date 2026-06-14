// Project-level build file.
// Plugins are declared here so every sub-module can use them,
// but `apply false` means they only activate inside each module's own build file.

plugins {
    id("com.android.application")         version "8.9.1" apply false
    id("org.jetbrains.kotlin.android")    version "2.1.0" apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.1.0" apply false
}
