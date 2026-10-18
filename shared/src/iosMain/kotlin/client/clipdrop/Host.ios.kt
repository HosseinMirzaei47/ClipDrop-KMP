package client.clipdrop

actual fun platformHost(): String = throw HostUndefinedException()

private class HostUndefinedException : Exception("Host is undefined")