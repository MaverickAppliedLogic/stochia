package com.example.stochia.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import com.example.stochia.ui.screen.common_components.CustomEditText
import com.example.stochia.ui.theme.LocalDimens
import com.example.stochia.ui.theme.Neutral
import com.example.stochia.ui.theme.NeutralDarker
import com.example.stochia.ui.theme.Primary
import com.example.stochia.ui.theme.PrimaryLightest
import com.example.stochia.ui.theme.SecondaryLight
import com.example.stochia.ui.theme.Typography
import com.example.stochia.ui.viewmodel.MainScreenEvent

@Composable
fun DistributionForm(
    onEvent: (MainScreenEvent) -> Unit
) {
    var data by remember { mutableStateOf("") }
    val dimens = LocalDimens.current
    val scrollState = rememberScrollState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(NeutralDarker)
            .verticalScroll(scrollState)
    ) {
        Spacer(modifier = Modifier.height(dimens.spacerXLarge))
        Text(
            "Distribución Probabilística",
            style = Typography.headlineLarge,
            textAlign = TextAlign.Center,
            color = PrimaryLightest
        )
        Spacer(modifier = Modifier.height(dimens.spacerMedium))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .clip(RoundedCornerShape(dimens.cornerRadiusMedium))
                .background(color = Neutral)
                .fillMaxWidth(0.9f)
                .padding(vertical = dimens.spacerLarge, horizontal = dimens.cardInnerPadding)
        ) {
            Text(
                "SECUENCIA DE DATOS INDEPENDIENTES",
                color = SecondaryLight,
                maxLines = 2,
                textAlign = TextAlign.Start,
                style = Typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(dimens.spacerSmall))
            CustomEditText(
                value = data,
                onValueChange = {
                    data = it
                    val result = Regex("\\d+(\\.\\d+)?")
                        .findAll(data)
                        .map { match -> match.value.toDouble() }
                        .toList()
                    onEvent(MainScreenEvent.ChangeDistributionData(result))
                },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(dimens.distributionFormDataInputHeight)
            )
            Spacer(modifier = Modifier.height(dimens.spacerLarge))
            Card(
                shape = RoundedCornerShape(dimens.cornerRadiusSmall),
                colors = CardDefaults.cardColors(
                    containerColor = Primary,
                    contentColor = SecondaryLight,
                    disabledContainerColor = Primary,
                    disabledContentColor = SecondaryLight
                ),
                modifier = Modifier
                    .fillMaxWidth(0.60f)
                    .height(dimens.commitButton),
                onClick = { onEvent(MainScreenEvent.DistributionButtonClicked) }
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    "ANALIZAR",
                    textAlign = TextAlign.Center,
                    style = Typography.bodyLarge,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.weight(1f))
            }
        }
        Spacer(modifier = Modifier.height(dimens.spacerXLarge))
    }
}
