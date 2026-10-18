package client.clipdrop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import client.clipdrop.ui.ClipboardScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            ClipboardScreen()
        }
    }
}

@Preview
@Composable
fun ClipboardScreenAndroidPreview() {
    ClipboardScreen()
}