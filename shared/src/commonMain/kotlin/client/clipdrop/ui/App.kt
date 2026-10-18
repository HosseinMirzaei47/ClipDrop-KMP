package client.clipdrop.ui

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
import client.clipdrop.data.ClipBoardStreamingEngine
import client.clipdrop.data.KtorSseClient
import client.clipdrop.platformHost

@Composable
fun App() {
    MaterialTheme {
        val client = remember { KtorSseClient.instance }
        val repository = remember {
            ClipBoardStreamingEngine(
                client = client,
                host = platformHost(),
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