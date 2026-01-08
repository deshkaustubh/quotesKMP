package tech.kaustubhdeshpande.quoteskmp

import kotlinx.serialization.Serializable

sealed interface QuotesRoute {
    @Serializable
    data object Home : QuotesRoute

    @Serializable
    data class Explore(val category: String?) : QuotesRoute

    @Serializable
    data object Saved : QuotesRoute
}
