package tech.kaustubhdeshpande.quoteskmp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

@Composable
fun QuotesKMPTheme(
    useDarkTheme: Boolean? = null,
    content: @Composable () -> Unit
) {
    val darkTheme = useDarkTheme ?: isSystemInDarkTheme()

    val colorScheme = if (darkTheme) {
        darkColorScheme(
            primary = AppColors.Purple80,
            secondary = AppColors.PurpleGrey80,
            tertiary = AppColors.Pink80
        )
    } else {
        lightColorScheme(
            primary = AppColors.Purple40,
            secondary = AppColors.PurpleGrey40,
            tertiary = AppColors.Pink40
        )
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}