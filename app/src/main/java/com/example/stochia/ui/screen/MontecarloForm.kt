package com.example.stochia.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.innerShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.stochia.ui.theme.Neutral
import com.example.stochia.ui.theme.NeutralDarker
import com.example.stochia.ui.theme.NeutralDarkest
import com.example.stochia.ui.theme.NeutralLight
import com.example.stochia.ui.theme.Primary
import com.example.stochia.ui.theme.PrimaryLightest
import com.example.stochia.ui.theme.SecondaryLight
import com.example.stochia.ui.theme.Typography
import com.example.stochia.ui.viewmodel.MainScreenEvent

@Composable
fun MontecarloForm(
    modifier: Modifier = Modifier,
    onClick: (MainScreenEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .background(color = Neutral)
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
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(color = NeutralLight)
                .innerShadow(
                    shape = RoundedCornerShape(10.dp),
                    shadow = Shadow(
                        radius = 10.dp,
                        spread = 5.dp,
                        color = NeutralDarker
                    )
                )
                .fillMaxWidth(0.9f)
                .weight(0.85f)
        )
        {
            Spacer(modifier = Modifier.weight(1f))
            Text("TIPO DE DISTRIBUCION",
                color = SecondaryLight,
                textAlign = TextAlign.Start,
                style = Typography.bodyLarge,
                modifier = Modifier
                    .fillMaxWidth(0.65f)
            )
            Spacer(modifier = Modifier.weight(0.05f))
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .background(color = NeutralLight)
                    .innerShadow(
                        shape = RoundedCornerShape(5.dp),
                        shadow = Shadow(
                            radius = 7.dp,
                            spread = 1.dp,
                            color = NeutralDarker
                        )
                    )
            ) {
                TextField(
                    value = "",
                    onValueChange = { /*TODO*/ },
                    colors = TextFieldDefaults.colors(
                        disabledContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                    ),
                    label = { },
                )
            }
            Spacer(modifier = Modifier.weight(0.5f))
            Text(
                "NUMERO DE INTERACCIONES (N)",
                color = SecondaryLight,
                textAlign = TextAlign.Start,
                style = Typography.bodyLarge,
                modifier = Modifier
                .fillMaxWidth(0.65f)

            )
            Spacer(modifier = Modifier.weight(0.05f))
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .background(color = NeutralLight)
                    .innerShadow(
                        shape = RoundedCornerShape(5.dp),
                        shadow = Shadow(
                            radius = 10.dp,
                            spread = 2.dp,
                            color = NeutralDarkest
                        )
                    )

            ) {
                TextField(
                    value = "",
                    onValueChange = { /*TODO*/ },
                    colors = TextFieldDefaults.colors(
                        disabledContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                    ),
                    label = {  },
                )
            }
            Spacer(modifier.weight(0.6f))
            Card(
                shape = RoundedCornerShape(5.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Primary,
                    contentColor = SecondaryLight,
                    disabledContainerColor = Primary,
                    disabledContentColor = SecondaryLight),
                modifier = Modifier
                    .fillMaxWidth(0.65f)
                    .height(70.dp),
                onClick = {TODO()}
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Text("SIMULAR",
                    textAlign = TextAlign.Center,
                    style = Typography.bodyLarge,
                    modifier = Modifier.weight(1f).fillMaxWidth(),
                )
                Spacer(modifier = Modifier.weight(1f))

            }
            Spacer(modifier = Modifier.weight(0.3f))
        }
        Spacer(modifier = Modifier.weight(0.05f))
    }

}

