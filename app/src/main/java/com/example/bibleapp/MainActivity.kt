package com.example.bibleapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bibleapp.ui.screens.ReaderScreen
import com.example.bibleapp.ui.screens.VotdScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // MaterialTheme wraps everything so all composables inherit
            // the correct colors, typography, and shapes automatically.
            MaterialTheme {
                BibleAppContent()
            }
        }
    }
}

/**
 * Root composable that owns the navigation controller and the bottom nav bar.
 *
 * Structure:
 *   Scaffold
 *   ├── bottomBar  → NavigationBar with "Today" and "Bible" tabs
 *   └── content    → NavHost that swaps between VotdScreen and ReaderScreen
 */
@Composable
fun BibleAppContent() {
    val navController = rememberNavController()

    // currentBackStackEntryAsState() re-reads the current destination
    // every time navigation happens, so the selected tab stays highlighted.
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStack?.destination?.route

    Scaffold(
        bottomBar = {
            NavigationBar {
                // ── Today (Verse of the Day) tab ──────────────────────
                NavigationBarItem(
                    selected = currentRoute == "votd",
                    onClick  = {
                        navController.navigate("votd") { launchSingleTop = true }
                    },
                    icon  = { Icon(Icons.Filled.WbSunny, contentDescription = "Verse of the Day") },
                    label = { Text("Today") }
                )
                // ── Bible Reader tab ───────────────────────────────────
                NavigationBarItem(
                    selected = currentRoute == "reader",
                    onClick  = {
                        navController.navigate("reader") { launchSingleTop = true }
                    },
                    icon  = { Icon(Icons.Filled.AutoStories, contentDescription = "Bible Reader") },
                    label = { Text("Bible") }
                )
            }
        }
    ) { innerPadding ->
        // innerPadding ensures content isn't hidden behind the bottom nav bar
        NavHost(
            navController    = navController,
            startDestination = "votd",              // open on the VOTD tab by default
            modifier         = Modifier.padding(innerPadding)
        ) {
            composable("votd")   { VotdScreen()   }
            composable("reader") { ReaderScreen() }
        }
    }
}
