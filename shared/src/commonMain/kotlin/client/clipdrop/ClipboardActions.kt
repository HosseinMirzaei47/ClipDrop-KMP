package client.clipdrop

interface ClipboardActions {
    fun write(text: String): Result<Unit>
}

class ClipboardUnavailableException : Exception()