package tech.kaustubhdeshpande.quoteskmp.share

expect class ShareManager {
    fun shareText(text: String, subject: String? = null)
}