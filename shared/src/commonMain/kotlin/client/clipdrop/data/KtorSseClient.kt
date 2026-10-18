package client.clipdrop.data

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.sse.SSE
import io.ktor.client.plugins.sse.SSEBufferPolicy

internal object KtorSseClient {
    val instance by lazy {
        HttpClient(CIO) {
            install(SSE) {
                bufferPolicy = SSEBufferPolicy.LastEvent
            }
        }
    }
}
