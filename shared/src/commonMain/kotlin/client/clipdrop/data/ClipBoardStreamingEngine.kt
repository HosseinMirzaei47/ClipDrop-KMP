package client.clipdrop.data

import io.ktor.client.HttpClient
import io.ktor.client.plugins.sse.sse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ClipBoardStreamingEngine(
    private val client: HttpClient,
    private val host: String,
) {
    fun watchClipboard(): Flow<String> = flow {
        client.sse(
            host = host,
            path = "/watch",
            port = 8090,
        ) {
            incoming.collect { event ->
                event.data?.let { emit(it) }
            }
        }
    }
}
