package com.example.stochia.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.dp
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
){
    var data by remember { mutableStateOf("") }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(NeutralDarker)
    ){
        Text(
            "Distribución Probabilística",
            style = Typography.headlineLarge,
            textAlign = TextAlign.Center,
            color = PrimaryLightest
        )
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(color = Neutral)
                .fillMaxSize(0.9f)
        ){
            Spacer(modifier = Modifier.weight(0.4f))
            Text(
                "SECUENCIA DE DATOS INDEPENDIENTES",
                color = SecondaryLight,
                maxLines = 2,
                textAlign = TextAlign.Start,
                style = Typography.bodyLarge,
                modifier = Modifier
            )
            Spacer(modifier = Modifier.weight(0.1f))
            CustomEditText(
                value = data ,
                onValueChange = {
                    data = it
                    val result = Regex("\\d+(\\.\\d+)?")
                            .findAll(data)
                            .map { it.value.toDouble() }
                            .toList()
                    onEvent(MainScreenEvent.ChangeDistributionData(result))
                },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(200.dp)
            )
            Spacer(modifier = Modifier.weight(0.4f))
            Card(
                shape = RoundedCornerShape(5.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Primary,
                    contentColor = SecondaryLight,
                    disabledContainerColor = Primary,
                    disabledContentColor = SecondaryLight
                ),
                modifier = Modifier
                    .fillMaxWidth(0.60f)
                    .height(LocalDimens.current.commitButton),
                onClick = {
                    onEvent(MainScreenEvent.DistributionButtonClicked)
                }
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    "ANALIZAR",
                    textAlign = TextAlign.Center,
                    style = Typography.bodyLarge,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                )
                Spacer(modifier = Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.weight(0.1f))
        }
    }
}