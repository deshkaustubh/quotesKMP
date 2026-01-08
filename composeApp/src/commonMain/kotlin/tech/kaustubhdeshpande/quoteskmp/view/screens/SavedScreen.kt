// kotlin
package tech.kaustubhdeshpande.quoteskmp.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tech.kaustubhdeshpande.quoteskmp.FavouriteViewModel
import tech.kaustubhdeshpande.quoteskmp.view.components.ExploreQuotesCard

@Composable
fun SavedScreen(
    modifier: Modifier = Modifier,
    favViewModel: FavouriteViewModel
) {
    val favorites = favViewModel.favorites

    if (favorites.isEmpty()) {
        Box(modifier = modifier.fillMaxSize()) {
            Text(text = "No favorites yet", style = MaterialTheme.typography.bodyMedium)
        }
    } else {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    text = "Favorites",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.headlineLarge,
                    fontSize = 32.sp
                )
            }

            items(favorites, key = { it.id }) { quote ->
                ExploreQuotesCard(
                    cardColor = Color(0xFF6750A4),
                    quote = quote,
                    quoteAuthor = quote.author,
                    category = quote.category.displayName,
                    favViewModel = favViewModel
                )
            }
        }
    }
}
