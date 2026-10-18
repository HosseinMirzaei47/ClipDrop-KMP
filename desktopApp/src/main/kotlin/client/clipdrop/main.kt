package client.clipdrop

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import client.clipdrop.di.initKoin
import client.clipdrop.ui.App

fun main() = application {
    initKoin()
    Window(
        onCloseRequest = ::exitApplication,
        title = "ClipDrop ",
    ) {
        App()
    }
}