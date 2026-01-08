package tech.kaustubhdeshpande.quoteskmp.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector


enum class QuoteCategory(val displayName: String, val icon: ImageVector, val bgColor: Color) {
    LIFE("Life", Icons.Filled.Favorite, Color(0xFF3B82F6)),        // Blue 500 — softer than 600/700
    MOTIVATION("Motivation", Icons.Filled.Star, Color(0xFFFBBF24)), // Amber 400 — warm but not neon
    SUCCESS(
        "Success",
        Icons.AutoMirrored.Filled.TrendingUp,
        Color(0xFF22C55E)
    ), // Green 500 — balanced
    WISDOM(
        "Wisdom",
        Icons.Filled.Info,
        Color(0xFF8B5CF6)
    ),        // Violet 500 — elegant and readable
    LOVE(
        "Love",
        Icons.Filled.FavoriteBorder,
        Color(0xFFEF4444)
    ),  // Red 500 — bold but not overpowering
    COURAGE("Courage", Icons.Filled.Shield, Color(0xFF06B6D4)),    // Cyan 500 — cool and clean
    LEADERSHIP(
        "Leadership",
        Icons.Filled.Groups,
        Color(0xFF64748B)
    ); // Slate 500 — grounded and neutral

    companion object {
        fun getCategory(name: String?): QuoteCategory? {
            return entries.firstOrNull {
                it.name.equals(name, ignoreCase = true)
            }
        }
    }
}
