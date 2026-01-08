package tech.kaustubhdeshpande.quoteskmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform