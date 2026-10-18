package client.clipdrop

import org.koin.dsl.module

actual val platformModule = module {
    single<ClipboardActions> { JvmClipboardActions() }
}