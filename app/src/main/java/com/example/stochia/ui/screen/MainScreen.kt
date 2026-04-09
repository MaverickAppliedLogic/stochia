package com.example.stochia.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.stochia.ui.screen.main_screen_components.BarButtonType
import com.example.stochia.ui.screen.main_screen_components.BottomBarButton
import com.example.stochia.ui.theme.Neutral
import com.example.stochia.ui.theme.NeutralDark
import com.example.stochia.ui.theme.PrimaryLight
import com.example.stochia.ui.theme.StochiaTheme
import com.example.stochia.ui.theme.Typography
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
            topBar = {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.08f)
                        .background(color = NeutralDark)
                ) {
                    Spacer(modifier = Modifier.weight(0.15f))
                    IconButton(
                        onClick = { TODO() },
                        colors = IconButtonDefaults.iconButtonColors(contentColor = PrimaryLight),
                        modifier = Modifier.size(40.dp)
                    ) {
                        Icon(
                            Icons.Default.Settings,
                            contentDescription = "Distribution",
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    Spacer(modifier = Modifier.weight(0.70f))
                    Text("STOCHASTIC ENGINE", style = Typography.headlineLarge, color = Color.White)
                    Spacer(modifier = Modifier.weight(1f))
                }
            },
            bottomBar = {
                BottomAppBar(
                    containerColor = Neutral,
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
                    .padding(padding)
                    .background(color = Neutral)
                    .fillMaxSize()
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
}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen()
}