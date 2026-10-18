package client.clipdrop

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context

class AndroidClipboardActions(
    private val context: Context,
) : ClipboardActions {

    override fun write(text: String): Result<Unit> {
        val clipboard = context.getSystemService(ClipboardManager::class.java)
            ?: return Result.failure(ClipboardUnavailableException())

        val clip = ClipData.newPlainText("text", text)

        clipboard.setPrimaryClip(clip)
        return Result.success(Unit)
    }
}
