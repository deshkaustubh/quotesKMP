// kotlin
package tech.kaustubhdeshpande.quoteskmp.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tech.kaustubhdeshpande.quoteskmp.FavouriteViewModel
import tech.kaustubhdeshpande.quoteskmp.data.Quote
import tech.kaustubhdeshpande.quoteskmp.data.QuoteCategory
import tech.kaustubhdeshpande.quoteskmp.view.components.ExploreQuotesCard

@Composable
fun ExploreScreen(
    modifier: Modifier = Modifier,
    initialCategoryName: String? = null,
    favViewModel: FavouriteViewModel
) {
    val categories: List<QuoteCategory?> = remember { listOf(null) + QuoteCategory.entries }

    val initialCategory =
        remember(initialCategoryName) { QuoteCategory.getCategory(initialCategoryName) }
    val initialIndex =
        remember(initialCategory) { categories.indexOf(initialCategory).takeIf { it >= 0 } ?: 0 }
    var selectedIndex by rememberSaveable { mutableIntStateOf(initialIndex) }

    // Cache the full list once (assuming it’s static). If it’s dynamic, load it in a ViewModel off the main thread.
    val allQuotes = remember { Quote.getQuotes() }

    // Derive filtered list cheaply, recomputing only when the selected category changes.
    val selectedCategory: QuoteCategory? = categories.getOrNull(selectedIndex)
    val filteredQuotes by remember(allQuotes, selectedCategory) {
        derivedStateOf {
            if (selectedCategory == null) allQuotes
            else allQuotes.filter { it.category == selectedCategory }
        }
    }

    LazyColumn(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
    ) {
        item {
            Text(
                text = "Categories",
                style = MaterialTheme.typography.headlineLarge,
                fontSize = 32.sp,
            )
        }

        item {
            ScrollableTabRow(
                selectedTabIndex = selectedIndex,
                edgePadding = 0.dp,
                indicator = {},
                divider = {},
                containerColor = MaterialTheme.colorScheme.surface
            ) {
                categories.forEachIndexed { index, cat ->
                    val isSelected = index == selectedIndex
                    val label = cat?.displayName ?: "All"
                    val bg = if (isSelected) cat?.bgColor ?: MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.surfaceVariant

                    Tab(
                        selected = isSelected,
                        onClick = { selectedIndex = index },
                        modifier = Modifier
                            .padding(top = 8.dp, bottom = 16.dp, end = 8.dp)
                            .background(color = bg, shape = RoundedCornerShape(56)),
                        text = {
                            Text(
                                text = label,
                                color = if (isSelected) Color.White else Color.Black
                            )
                        }
                    )
                }
            }
        }

        items(filteredQuotes, key = { it.id }) { q ->
            ExploreQuotesCard(
                modifier = modifier.padding(vertical = 8.dp),
                cardColor = q.category.bgColor,
                quote = q,
                quoteAuthor = q.author,
                category = q.category.displayName,
                favViewModel = favViewModel
            )
            Spacer(modifier = modifier.height(16.dp))
        }
    }
}
