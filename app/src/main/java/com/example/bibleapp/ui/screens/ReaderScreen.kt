package com.example.bibleapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.bibleapp.data.BibleData
import com.youversion.platform.core.bibles.domain.BibleReference
import com.youversion.platform.ui.views.BibleText

/**
 * "Bible" screen — the main reading experience.
 *
 * Layout (top → bottom):
 *   ┌─────────────────────────────────────────────────────┐
 *   │  [BSB ▾]  [Genesis                  ▾]  [1 ▾]      │  ← selector row
 *   ├─────────────────────────────────────────────────────┤
 *   │  ← Prev          Genesis 1          Next →          │  ← chapter nav
 *   ├─────────────────────────────────────────────────────┤
 *   │                                                     │
 *   │   (scrollable Bible text from YouVersion SDK)       │
 *   │                                                     │
 *   └─────────────────────────────────────────────────────┘
 */
@Composable
fun ReaderScreen() {

    // ── State ──────────────────────────────────────────────────────────
    var selectedVersion by remember { mutableStateOf(BibleData.versions.first()) }
    var selectedBook    by remember { mutableStateOf(BibleData.books.first()) }
    var selectedChapter by remember { mutableStateOf(1) }

    // scrollState is kept separate so we can reset to top when the passage changes
    val scrollState = rememberScrollState()

    // Scroll back to the top whenever the user navigates to a different passage
    LaunchedEffect(selectedVersion.id, selectedBook.usfm, selectedChapter) {
        scrollState.scrollTo(0)
    }

    Column(modifier = Modifier.fillMaxSize()) {

        // ── Row 1: Version / Book / Chapter dropdowns ──────────────────
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            // Version selector — shows abbreviation (e.g. "BSB")
            DropdownSelector(
                label       = selectedVersion.abbreviation,
                options     = BibleData.versions,
                displayText = { it.name },
                onSelected  = { selectedVersion = it },
                modifier    = Modifier.weight(1f)
            )

            // Book selector — shows full book name
            DropdownSelector(
                label       = selectedBook.name,
                options     = BibleData.books,
                displayText = { it.name },
                onSelected  = {
                    selectedBook    = it
                    selectedChapter = 1     // always reset to ch. 1 on book change
                },
                modifier = Modifier.weight(1.6f)
            )

            // Chapter selector — shows chapter number only
            DropdownSelector(
                label       = selectedChapter.toString(),
                options     = (1..selectedBook.chapters).toList(),
                displayText = { it.toString() },
                onSelected  = { selectedChapter = it },
                modifier    = Modifier.weight(0.7f)
            )
        }

        // ── Row 2: Prev / current location label / Next ─────────────────
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment     = Alignment.CenterVertically
        ) {
            TextButton(onClick = {
                if (selectedChapter > 1) {
                    // Move back one chapter within the same book
                    selectedChapter--
                } else {
                    // At chapter 1 — jump to the last chapter of the previous book
                    val idx = BibleData.books.indexOf(selectedBook)
                    if (idx > 0) {
                        val prev        = BibleData.books[idx - 1]
                        selectedBook    = prev
                        selectedChapter = prev.chapters
                    }
                }
            }) {
                Text("← Prev")
            }

            // Shows e.g. "Genesis  1  •  BSB" in the centre
            Text(
                text  = "${selectedBook.name}  ${selectedChapter}  •  ${selectedVersion.abbreviation}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            TextButton(onClick = {
                if (selectedChapter < selectedBook.chapters) {
                    // Move forward one chapter within the same book
                    selectedChapter++
                } else {
                    // At last chapter — jump to chapter 1 of the next book
                    val idx = BibleData.books.indexOf(selectedBook)
                    if (idx < BibleData.books.size - 1) {
                        selectedBook    = BibleData.books[idx + 1]
                        selectedChapter = 1
                    }
                }
            }) {
                Text("Next →")
            }
        }

        HorizontalDivider()

        // ── Scrollable Bible text ────────────────────────────────────────
        // BibleText is a YouVersion SDK composable. Give it a BibleReference
        // and it fetches and renders the full chapter text automatically,
        // with correct formatting for each Bible version.
        //
        // key() forces Compose to throw away and recreate the composable
        // whenever the reference changes, which prevents stale text flashing.
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            key(selectedVersion.id, selectedBook.usfm, selectedChapter) {
                BibleText(
                    reference = BibleReference(
                        versionId = selectedVersion.id,
                        bookUSFM  = selectedBook.usfm,
                        chapter   = selectedChapter
                    )
                )
            }
        }
    }
}

/**
 * A generic reusable dropdown button.
 *
 * Shows [label] on a button; when tapped, opens a scrollable menu listing
 * every item in [options]. Calls [onSelected] and closes when an item is picked.
 *
 * @param T          The type of the option items (BibleVersion, BibleBook, Int, …)
 * @param label      Text shown on the closed button
 * @param options    Full list of items to show in the dropdown
 * @param displayText Converts an option to the string shown in the menu row
 * @param onSelected Called with the chosen item when the user taps a row
 * @param modifier   Applied to the outermost Box (use for sizing / weight)
 */
@Composable
fun <T> DropdownSelector(
    label:       String,
    options:     List<T>,
    displayText: (T) -> String,
    onSelected:  (T) -> Unit,
    modifier:    Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        // The visible button the user taps to open the dropdown
        OutlinedButton(
            onClick        = { expanded = true },
            modifier       = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 0.dp)
        ) {
            Text(
                text     = label,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f)
            )
            Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
        }

        // The dropdown menu — Material3 clips it to a max height and scrolls automatically
        DropdownMenu(
            expanded          = expanded,
            onDismissRequest  = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text    = { Text(displayText(option)) },
                    onClick = {
                        onSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}
