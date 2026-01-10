package tech.kaustubhdeshpande.quoteskmp.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tech.kaustubhdeshpande.quoteskmp.FavouriteViewModel
import tech.kaustubhdeshpande.quoteskmp.data.Quote
import tech.kaustubhdeshpande.quoteskmp.share.ShareManager

@Composable
fun QuotesCard(
    modifier: Modifier = Modifier,
    cardColor: Color,
    quoteText: String,
    quoteAuthor: String,
    quote: Quote,
    favViewModel: FavouriteViewModel
) {

    val isFavorite: Boolean = favViewModel.favorites.any { it.id == quote.id }

    Card(
        modifier = Modifier
            .height(240.dp)
            .width(200.dp)
            .clip(shape = RoundedCornerShape(16.dp))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            cardColor,
                            cardColor.copy(0.8f),
                            cardColor.copy(0.6f)
                        )
                    )
                )
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape),
                    color = Color.White.copy(0.2f)
                ) {}

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = {
                            if (isFavorite) {
                                favViewModel.removeFavorite(quote)
                            } else {
                                favViewModel.addFavorite(quote)
                            }
                        }
                    ) {
                        Icon(
                            imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                            contentDescription = if (isFavorite) "Unfavorite" else "favorite",
                            tint = Color.White
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = quoteText,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 16.sp,
                    lineHeight = 16.sp,
                    color = Color.White
                ),
                textAlign = TextAlign.Left
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "-  $quoteAuthor",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 14.sp,
                    fontStyle = FontStyle.Italic,
                    lineHeight = 16.sp,
                    color = Color.White
                )
            )
        }
    }
}