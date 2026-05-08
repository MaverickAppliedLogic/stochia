package com.example.stochia.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.example.stochia.domain.model.montecarlo.MontecarloParams
import com.example.stochia.ui.screen.montecarlo_form_components.MontecarloFormFields
import com.example.stochia.ui.theme.LocalDimens
import com.example.stochia.ui.theme.NeutralDarker
import com.example.stochia.ui.theme.PrimaryLightest
import com.example.stochia.ui.theme.Typography
import com.example.stochia.ui.viewmodel.MainScreenEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MontecarloForm(
    params: MontecarloParams,
    onEvent: (MainScreenEvent) -> Unit
) {
    val dimens = LocalDimens.current
    val scrollState = rememberScrollState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(NeutralDarker)
            .verticalScroll(scrollState)
    ) {
        Spacer(modifier = Modifier.height(dimens.spacerLarge))
        Text(
            "Simulaciones Monte Carlo",
            style = Typography.headlineLarge,
            textAlign = TextAlign.Center,
            color = PrimaryLightest
        )
        Spacer(modifier = Modifier.height(dimens.spacerMedium))
        MontecarloFormFields(
            allParams = params,
            modifier = Modifier.fillMaxWidth(),
            onEvent = { onEvent(it) }
        )
        Spacer(modifier = Modifier.height(dimens.spacerLarge))
    }
}
