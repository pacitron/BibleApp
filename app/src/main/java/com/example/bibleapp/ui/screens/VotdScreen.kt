package com.example.bibleapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.youversion.platform.ui.views.votd.VerseOfTheDay
import java.text.SimpleDateFormat
import java.util.*

/**
 * "Today" screen — shows the current day's Verse of the Day.
 *
 * [VerseOfTheDay] is a ready-made SDK composable. It automatically
 * fetches and renders today's verse with the reference and copyright notice.
 * No parameters required.
 */
@Composable
fun VotdScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())   // scroll if text is long
            .padding(horizontal = 20.dp, vertical = 28.dp)
    ) {
        // Show the current date at the top as a soft label
        Text(
            text  = SimpleDateFormat("EEEE, MMMM d", Locale.getDefault()).format(Date()),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text       = "Verse of the Day",
            style      = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(28.dp))

        // ── YouVersion SDK component ───────────────────────────────────
        // Fetches today's verse automatically based on the current day of year.
        // Handles its own loading and error states internally.
        VerseOfTheDay()

        Spacer(modifier = Modifier.height(24.dp))
    }
}
