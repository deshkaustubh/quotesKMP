package tech.kaustubhdeshpande.quoteskmp.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tech.kaustubhdeshpande.quoteskmp.FavouriteViewModel
import tech.kaustubhdeshpande.quoteskmp.data.Quote
import tech.kaustubhdeshpande.quoteskmp.data.QuoteCategory
import tech.kaustubhdeshpande.quoteskmp.view.components.CategoryCard
import tech.kaustubhdeshpande.quoteskmp.view.components.QuoteBanner
import tech.kaustubhdeshpande.quoteskmp.view.components.QuotesCard
import tech.kaustubhdeshpande.quoteskmp.view.components.SectionHeader

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onNavigateToExplore: (category: String?) -> Unit = {},
    onToggleTheme: () -> Unit,
    favViewModel: FavouriteViewModel
) {
    LazyColumn(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Explore",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.displayLarge.copy(
                        fontSize = 48.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                )
                IconButton(onClick = onToggleTheme) {
                    val isDark = MaterialTheme.colorScheme.background.luminance() < 0.5f
                    Icon(
                        imageVector = if (isDark) Icons.Filled.LightMode else Icons.Filled.DarkMode,
                        contentDescription = "Toggle theme"
                    )
                }
            }
            Text(
                text = "Awesome quotes from our community",
                fontWeight = FontWeight.Light,
                style = MaterialTheme.typography.displaySmall.copy(fontSize = 12.sp),
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
        item {
            QuoteBanner(modifier = modifier)
        }
        item {
            SectionHeader(
                modifier = modifier,
                startText = "Latest Quotes",
                endText = "View All",
                onNavigate = { onNavigateToExplore(null) }
            )
            Spacer(modifier = Modifier.height(16.dp))

            val quotes = Quote.getQuotes()
            LazyRow(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(quotes) { q ->
                    QuotesCard(
                        modifier = modifier,
                        cardColor = q.category.bgColor,
                        quoteText = q.text,
                        quoteAuthor = q.author,
                        quote = q,
                        favViewModel = favViewModel
                    )
                }
            }
        }

        item {
            SectionHeader(
                modifier = modifier,
                startText = "Categories",
                endText = "View All",
                onNavigate = { onNavigateToExplore(null) }
            )
            Spacer(modifier = Modifier.height(16.dp))
            // we are using .draw behind to avoid nesting in this case -> chat Akshay N
            LazyRow(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(8.dp)) {

                // getting the enum class
                val categories = QuoteCategory.values()

                items(categories.toList()) { category ->
                    CategoryCard(
                        modifier = Modifier
                            .clickable { onNavigateToExplore(category.name) }
                            .then(modifier),
                        categoryColor = category.bgColor,
                        category = category.displayName,
                        categoryIcon = category.icon
                    )
                }
            }
        }

        item {
            SectionHeader(
                modifier = modifier,
                startText = "Trending Quotes",
                endText = "View All",
                onNavigate = { onNavigateToExplore(null) }
            )
            Spacer(modifier = modifier.height(16.dp))

            val quotes = Quote.getQuotes()

            LazyRow(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(8.dp)) {

                items(quotes) { q ->
                    QuotesCard(
                        modifier = modifier,
                        cardColor = q.category.bgColor,
                        quoteText = q.text,
                        quoteAuthor = q.author,
                        quote = q,
                        favViewModel = favViewModel
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}