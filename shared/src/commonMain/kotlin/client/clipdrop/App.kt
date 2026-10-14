package client.clipdrop

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.ktor.client.HttpClient
import io.ktor.client.plugins.sse.SSE

@Composable
fun App() {
    MaterialTheme {
        val client = remember {
            HttpClient {
                install(SSE)
            }
        }
        val repository = remember {
            ClipBoardStreamingSseEngine(
                client = client,
                host = "localhost"
            )
        }

        var latestClip by remember { mutableStateOf("Waiting for clipboard...") }

        LaunchedEffect(Unit) {
            repository.watchClipboard().collect { text ->
                latestClip = text
            }
        }

        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Latest from Mac:", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = latestClip, style = MaterialTheme.typography.bodyLarge)
        }
    }
}