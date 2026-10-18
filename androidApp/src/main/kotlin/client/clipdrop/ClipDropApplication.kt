package client.clipdrop

import android.app.Application
import client.clipdrop.di.initKoin
import org.koin.android.ext.koin.androidContext

class ClipDropApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@ClipDropApplication)
        }
    }
}