package client.clipdrop.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.compose.KoinContext
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App() {
    MaterialTheme {
        KoinContext {
            val viewModel: ClipboardViewModel = koinViewModel()
            val state by viewModel.state.collectAsState()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                when (val current = state) {
                    is ClipboardUiState.Connecting -> {
                        CircularProgressIndicator()
                        Spacer(modifier = Modifier.height(16.dp))
                        Text("Connecting to ClipDrop daemon...")
                    }

                    is ClipboardUiState.Success -> {
                        Text(
                            text = "Latest Clipboard",
                            style = MaterialTheme.typography.titleMedium,
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = current.text,
                            style = MaterialTheme.typography.bodyLarge,
                        )
                    }

                    is ClipboardUiState.Error -> {
                        Text(
                            text = "Error: ${current.message}",
                            color = MaterialTheme.colorScheme.error,
                        )
                    }
                }
            }
        }
    }
}