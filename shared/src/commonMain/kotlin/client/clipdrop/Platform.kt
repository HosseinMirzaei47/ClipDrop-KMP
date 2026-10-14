package client.clipdrop

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform