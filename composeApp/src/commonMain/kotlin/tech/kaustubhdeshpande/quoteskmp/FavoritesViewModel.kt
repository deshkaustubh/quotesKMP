package tech.kaustubhdeshpande.quoteskmp

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import tech.kaustubhdeshpande.quoteskmp.data.Quote

class FavouriteViewModel : ViewModel() {
    private val _favorites = mutableStateListOf<Quote>()
    val favorites: List<Quote> get() = _favorites

    fun addFavorite(quote: Quote) {
        if (_favorites.none { it.id == quote.id }) {
            _favorites.add(quote)
        }
    }

    fun removeFavorite(quote: Quote) {
        _favorites.removeAll { it.id == quote.id }
    }

    fun isFavorite(id: Int): Boolean = _favorites.any { it.id == id }
}
