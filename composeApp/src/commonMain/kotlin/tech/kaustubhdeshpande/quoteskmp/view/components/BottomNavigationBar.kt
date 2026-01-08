package tech.kaustubhdeshpande.quoteskmp.view.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import tech.kaustubhdeshpande.quoteskmp.QuotesRoute

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    isHomeSelected: Boolean,
    isExploreSelected: Boolean,
    isSavedSelected: Boolean,
    onNavigate: (QuotesRoute) -> Unit
) {
    NavigationBar(modifier = modifier) {
        NavigationBarItem(
            selected = isHomeSelected,
            onClick = { onNavigate(QuotesRoute.Home) },
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
            label = { Text("Home") }
        )
        NavigationBarItem(
            selected = isExploreSelected,
            onClick = { onNavigate(QuotesRoute.Explore(null)) },
            icon = { Icon(Icons.Filled.Search, contentDescription = "Explore") },
            label = { Text("Explore") }
        )
        NavigationBarItem(
            selected = isSavedSelected,
            onClick = { onNavigate(QuotesRoute.Saved) },
            icon = { Icon(Icons.Filled.Favorite, contentDescription = "Favorites") },
            label = { Text("Favorites") }
        )
    }
}