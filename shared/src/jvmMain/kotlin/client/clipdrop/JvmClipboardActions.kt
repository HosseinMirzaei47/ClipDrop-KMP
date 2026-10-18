package client.clipdrop

import java.awt.Toolkit
import java.awt.datatransfer.StringSelection

class JvmClipboardActions : ClipboardActions {

    override fun write(text: String): Result<Unit> {
        val selection = StringSelection(text)
        val clipboard = Toolkit.getDefaultToolkit().systemClipboard ?: return Result.failure(ClipboardUnavailableException())

        clipboard.setContents(selection, selection)
        return Result.success(Unit)
    }
}
