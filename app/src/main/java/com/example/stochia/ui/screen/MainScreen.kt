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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.stochia.ui.screen.main_screen_components.BarButtonType
import com.example.stochia.ui.screen.main_screen_components.BottomBarButton
import com.example.stochia.ui.theme.Neutral
import com.example.stochia.ui.theme.NeutralDark
import com.example.stochia.ui.theme.PrimaryLight
import com.example.stochia.ui.theme.Typography
import com.example.stochia.ui.viewmodel.MainViewModel
import com.example.stochia.ui.viewmodel.Screen
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel = koinViewModel()
) {
    val current = viewModel.currentScreen
    Scaffold(
        contentWindowInsets = WindowInsets.safeDrawing,
        modifier = Modifier
            .fillMaxSize()
            .background(NeutralDark),
        topBar = {
            TopAppBar(
                title = {
                    Row(modifier = Modifier.fillMaxWidth(0.9f),
                        horizontalArrangement = Arrangement.Center) {
                            Text("STOCHASTIC ENGINE",
                                style = Typography.headlineLarge,
                                color = Color.White,
                                modifier = Modifier.blur(0.5.dp)
                            )
                    }

                },
                navigationIcon = {
                    IconButton(
                        onClick = { TODO() },
                        colors = IconButtonDefaults.iconButtonColors(contentColor = PrimaryLight),
                        modifier = Modifier
                    ) {
                        Icon(
                            Icons.Default.Settings,
                            contentDescription = "Distribution",
                            modifier = Modifier
                        )
                    }
                }

            )

        },
        bottomBar = {
            BottomAppBar(
                containerColor = NeutralDark,
                modifier = Modifier.fillMaxHeight(0.11f)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .background(color = NeutralDark)
                        .fillMaxSize()
                ) {
                    Spacer(modifier = Modifier.weight(0.50f))
                    BottomBarButton(
                        current = current,
                        type = BarButtonType.DISTRIBUTION,
                        onClick = { viewModel.navigateTo(Screen.DISTRIBUTION) }
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    BottomBarButton(
                        current = current,
                        type = BarButtonType.MONTECARLO,
                        onClick = { viewModel.navigateTo(Screen.MONTECARLO) }
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    BottomBarButton(
                        current = current,
                        type = BarButtonType.MARKOV,
                        onClick = { viewModel.navigateTo(Screen.MARKOV) }
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
            when (current) {
                Screen.RESULT -> ResultForm()
                Screen.DISTRIBUTION -> DistributionForm()
                Screen.MONTECARLO -> MontecarloForm()
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