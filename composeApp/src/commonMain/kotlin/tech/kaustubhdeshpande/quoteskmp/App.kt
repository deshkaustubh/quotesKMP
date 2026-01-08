package tech.kaustubhdeshpande.quoteskmp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.jetbrains.compose.ui.tooling.preview.Preview
import tech.kaustubhdeshpande.quoteskmp.ui.theme.QuotesKMPTheme

@Composable
@Preview
fun App() {
    var useDarkTheme by remember { mutableStateOf<Boolean?>(null) } // hoist this outside so that it does not follow system theme
    QuotesKMPTheme(useDarkTheme = useDarkTheme) {
        AppNavGraph(
            onToggleTheme = {
                // null -> explicit dark/ light
                useDarkTheme = when (useDarkTheme) {
                    null -> true // go dark
                    true -> false // go light
                    false -> true // system theme
                }
            }
        )
    }
}