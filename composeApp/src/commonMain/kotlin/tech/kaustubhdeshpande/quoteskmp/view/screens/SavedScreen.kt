// kotlin
package tech.kaustubhdeshpande.quoteskmp.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Box(
                    modifier = Modifier.background(
                        shape = CircleShape,
                        color = Color(0xff4c3384).copy(0.5f)
                    )
                        .size(120.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = "Favorite",
                        modifier = Modifier.size(56.dp),
                        tint = Color(0xff7851d1)
                    )
                }
                Spacer(modifier = Modifier.height(32.dp))
            }
            item {
                Text(
                    text = "No favorites yet",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 24.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
            item {
                Text(
                    text = "Tap the heart icon on any item to save\n it here for quick access",
                    fontWeight = FontWeight.ExtraLight,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )
            }
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
