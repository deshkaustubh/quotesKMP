package tech.kaustubhdeshpande.quoteskmp.share

import android.content.Context
import android.content.Intent

actual class ShareManager(private val context: Context) {
    actual fun shareText(text: String, subject: String?) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, text)
            subject?.let { putExtra(Intent.EXTRA_SUBJECT, it) }
        }
        val chooser = Intent.createChooser(intent, subject ?: "Share")
        chooser.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(chooser)
    }
}
