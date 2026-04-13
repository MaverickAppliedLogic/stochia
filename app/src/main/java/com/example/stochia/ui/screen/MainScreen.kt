package com.example.stochia.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.stochia.ui.screen.main_screen_components.BottomBarButton
import com.example.stochia.ui.screen.main_screen_components.SettingsButton
import com.example.stochia.ui.theme.Neutral
import com.example.stochia.ui.theme.NeutralDarker
import com.example.stochia.ui.theme.Typography
import com.example.stochia.ui.viewmodel.MainViewModel
import com.example.stochia.ui.viewmodel.Screen
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        contentWindowInsets = WindowInsets.safeDrawing,
        containerColor = NeutralDarker,
        modifier = Modifier
            .fillMaxSize()
            .background(NeutralDarker),
        topBar = {
            TopAppBar(
                title = {
                    Row(modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .background(NeutralDarker),
                        horizontalArrangement = Arrangement.Center) {
                            Text("STOCHIA",
                                style = Typography.headlineLarge,
                                color = Color.White,
                            )
                    }

                },
                navigationIcon = {
                    SettingsButton(
                        selected = state.settingsVisible,
                        onClick = {viewModel.onEvent(it)},
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = NeutralDarker)
            )

        },
        bottomBar = {
            BottomAppBar(
                containerColor = NeutralDarker,
                modifier = Modifier.fillMaxHeight(0.11f)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .background(color = NeutralDarker)
                        .fillMaxSize()
                ) {
                    Spacer(modifier = Modifier.weight(0.50f))
                    BottomBarButton(
                        currentScreen = state.currentScreen,
                        type = Screen.DISTRIBUTION,
                        onClick = { viewModel.onEvent(it) }
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    BottomBarButton(
                        currentScreen = state.currentScreen,
                        type = Screen.MONTECARLO,
                        onClick = { viewModel.onEvent(it) }
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    BottomBarButton(
                        currentScreen = state.currentScreen,
                        type = Screen.MARKOV,
                        onClick = { viewModel.onEvent(it) }
                    )
                    Spacer(modifier = Modifier.weight(0.50f))
                }
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Neutral)
                .padding(padding)
        ) {
            when (state.currentScreen) {
                Screen.RESULT -> ResultForm()
                Screen.DISTRIBUTION -> DistributionForm()
                Screen.MONTECARLO -> MontecarloForm(
                    modifier = Modifier.padding(padding),
                    onClick = {}
                )
                Screen.MARKOV -> MarkovForm()
            }
        }
    }
}


@Preview
@Composable
fun MainScreenPreview() {
    MainScreen()
}