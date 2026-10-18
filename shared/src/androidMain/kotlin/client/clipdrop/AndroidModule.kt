package client.clipdrop

import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single<ClipboardActions> {
        AndroidClipboardActions(context = androidContext())
    }
}