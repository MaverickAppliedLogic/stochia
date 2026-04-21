package com.example.stochia.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.example.stochia.ui.screen.montecarlo_form_components.DistributionType
import com.example.stochia.ui.screen.montecarlo_form_components.MontecarloFormFields
import com.example.stochia.ui.theme.NeutralDarker
import com.example.stochia.ui.theme.PrimaryLightest
import com.example.stochia.ui.theme.Typography
import com.example.stochia.ui.viewmodel.MainScreenEvent



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MontecarloForm(
    type: DistributionType,
    modifier: Modifier = Modifier,
    onEvent: (MainScreenEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .background(color = NeutralDarker)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.weight(0.05f))
        Text(
            "Simulaciones Monte Carlo",
            style = Typography.headlineLarge,
            textAlign = TextAlign.Center,
            color = PrimaryLightest
        )
        Spacer(modifier = Modifier.weight(0.05f))
        MontecarloFormFields(
            type = type,
            modifier = Modifier.weight(0.85f),
            onClick = {onEvent(it)}
        )
        Spacer(modifier = Modifier.weight(0.05f))
    }

}

