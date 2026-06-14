package com.example.bibleapp.data

/**
 * Represents one Bible translation available through the YouVersion SDK.
 *
 * @param name         Full display name shown in the dropdown list
 * @param abbreviation Short label shown on the selector button (e.g. "BSB")
 * @param id           YouVersion version ID passed to [BibleReference]
 *
 * Note: a version only works if you have accepted its license agreement
 * at platform.youversion.com.  BSB (3034) is a safe default — it is used
 * in all YouVersion SDK examples.
 */
data class BibleVersion(
    val name: String,
    val abbreviation: String,
    val id: Int
)

/**
 * Represents one book of the Bible.
 *
 * @param name     Display name (e.g. "Genesis")
 * @param usfm     USFM book code required by [BibleReference] (e.g. "GEN")
 * @param chapters Total number of chapters in this book
 */
data class BibleBook(
    val name: String,
    val usfm: String,
    val chapters: Int
)

/** Static reference data used by the version, book, and chapter selectors. */
object BibleData {

    val versions = listOf(
        BibleVersion("Berean Standard Bible",    "BSB", 3034),
        BibleVersion("King James Version",        "KJV",    1),
        BibleVersion("English Standard Version",  "ESV",   59),
        BibleVersion("New International Version", "NIV",  111),
        BibleVersion("New Living Translation",    "NLT",   97),
    )

    val books = listOf(
        // ── Old Testament ─────────────────────────────────────────────
        BibleBook("Genesis",         "GEN",  50),
        BibleBook("Exodus",          "EXO",  40),
        BibleBook("Leviticus",       "LEV",  27),
        BibleBook("Numbers",         "NUM",  36),
        BibleBook("Deuteronomy",     "DEU",  34),
        BibleBook("Joshua",          "JOS",  24),
        BibleBook("Judges",          "JDG",  21),
        BibleBook("Ruth",            "RUT",   4),
        BibleBook("1 Samuel",        "1SA",  31),
        BibleBook("2 Samuel",        "2SA",  24),
        BibleBook("1 Kings",         "1KI",  22),
        BibleBook("2 Kings",         "2KI",  25),
        BibleBook("1 Chronicles",    "1CH",  29),
        BibleBook("2 Chronicles",    "2CH",  36),
        BibleBook("Ezra",            "EZR",  10),
        BibleBook("Nehemiah",        "NEH",  13),
        BibleBook("Esther",          "EST",  10),
        BibleBook("Job",             "JOB",  42),
        BibleBook("Psalms",          "PSA", 150),
        BibleBook("Proverbs",        "PRO",  31),
        BibleBook("Ecclesiastes",    "ECC",  12),
        BibleBook("Song of Solomon", "SNG",   8),
        BibleBook("Isaiah",          "ISA",  66),
        BibleBook("Jeremiah",        "JER",  52),
        BibleBook("Lamentations",    "LAM",   5),
        BibleBook("Ezekiel",         "EZK",  48),
        BibleBook("Daniel",          "DAN",  12),
        BibleBook("Hosea",           "HOS",  14),
        BibleBook("Joel",            "JOL",   3),
        BibleBook("Amos",            "AMO",   9),
        BibleBook("Obadiah",         "OBA",   1),
        BibleBook("Jonah",           "JON",   4),
        BibleBook("Micah",           "MIC",   7),
        BibleBook("Nahum",           "NAM",   3),
        BibleBook("Habakkuk",        "HAB",   3),
        BibleBook("Zephaniah",       "ZEP",   3),
        BibleBook("Haggai",          "HAG",   2),
        BibleBook("Zechariah",       "ZEC",  14),
        BibleBook("Malachi",         "MAL",   4),
        // ── New Testament ─────────────────────────────────────────────
        BibleBook("Matthew",         "MAT",  28),
        BibleBook("Mark",            "MRK",  16),
        BibleBook("Luke",            "LUK",  24),
        BibleBook("John",            "JHN",  21),
        BibleBook("Acts",            "ACT",  28),
        BibleBook("Romans",          "ROM",  16),
        BibleBook("1 Corinthians",   "1CO",  16),
        BibleBook("2 Corinthians",   "2CO",  13),
        BibleBook("Galatians",       "GAL",   6),
        BibleBook("Ephesians",       "EPH",   6),
        BibleBook("Philippians",     "PHP",   4),
        BibleBook("Colossians",      "COL",   4),
        BibleBook("1 Thessalonians", "1TH",   5),
        BibleBook("2 Thessalonians", "2TH",   3),
        BibleBook("1 Timothy",       "1TI",   6),
        BibleBook("2 Timothy",       "2TI",   4),
        BibleBook("Titus",           "TIT",   3),
        BibleBook("Philemon",        "PHM",   1),
        BibleBook("Hebrews",         "HEB",  13),
        BibleBook("James",           "JAS",   5),
        BibleBook("1 Peter",         "1PE",   5),
        BibleBook("2 Peter",         "2PE",   3),
        BibleBook("1 John",          "1JN",   5),
        BibleBook("2 John",          "2JN",   1),
        BibleBook("3 John",          "3JN",   1),
        BibleBook("Jude",            "JUD",   1),
        BibleBook("Revelation",      "REV",  22),
    )
}
