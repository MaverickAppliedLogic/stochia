package com.example.stochia.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.stochia.ui.theme.StochiaTheme
import com.example.stochia.ui.viewmodel.MainViewModel
import com.example.stochia.ui.viewmodel.Screen
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    viewModel: MainViewModel = koinViewModel()
) {
    val current = viewModel.currentScreen
    StochiaTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                BottomAppBar {
                    IconButton(onClick = { viewModel.navigateTo(Screen.DISTRIBUTION) }) {
                        Icon(Icons.Default.AddCircle, contentDescription = "Distribution")
                    }
                    IconButton(onClick = { viewModel.navigateTo(Screen.MONTECARLO) }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "Montecarlo")
                    }
                    IconButton(onClick = { viewModel.navigateTo(Screen.MARKOV) }) {
                        Icon(Icons.Default.PlayArrow, contentDescription = "Markov")
                    }
                }
            }
        ) { padding ->
            Box(modifier = Modifier.padding(padding)) {
                when (current) {
                    Screen.RESULT -> ResultForm()
                    Screen.DISTRIBUTION -> DistributionForm()
                    Screen.MONTECARLO -> MontecarloForm()
                    Screen.MARKOV -> MarkovForm()
                }
            }
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen()
}