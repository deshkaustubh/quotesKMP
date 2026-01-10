// Kotlin
package tech.kaustubhdeshpande.quoteskmp.share

import java.awt.Desktop
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import java.net.URI

actual class ShareManager {
    actual fun shareText(text: String, subject: String?) {
        // Copy to clipboard
        try {
            val clipboard = Toolkit.getDefaultToolkit().systemClipboard
            clipboard.setContents(StringSelection(text), null)
        } catch (_: Throwable) {
            // ignore clipboard failures
        }

        // Try to open default mail client
        try {
            if (Desktop.isDesktopSupported()) {
                val subj = subject?.let { java.net.URLEncoder.encode(it, "UTF-8") } ?: ""
                val body = java.net.URLEncoder.encode(text, "UTF-8")
                val mailto = "mailto:?subject=$subj&body=$body"
                Desktop.getDesktop().browse(URI(mailto))
                return
            }
        } catch (_: Throwable) {
            // ignore desktop failures
        }

        // No-op if neither is available
    }
}
