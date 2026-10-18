package client.clipdrop.di

import client.clipdrop.data.ClipboardRepository
import client.clipdrop.platformHost
import client.clipdrop.ui.ClipboardViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.sse.SSE
import io.ktor.client.plugins.sse.SSEBufferPolicy
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

private val appModule = module {
    single {
        HttpClient(CIO) {
            install(SSE) {
                bufferPolicy = SSEBufferPolicy.LastEvent
            }
        }
    }

    single<ClipboardRepository> {
        ClipboardRepository(
            client = get(),
            host = platformHost(),
        )
    }

    viewModel { ClipboardViewModel(clipboardRepository = get()) }
}

fun initKoin(appDeclaration: KoinAppDeclaration = {}) {
    startKoin {
        appDeclaration()
        modules(appModule)
    }
}