package com.example.stochia.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.innerShadow
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.unit.dp
import com.example.stochia.ui.theme.Neutral
import com.example.stochia.ui.theme.NeutralDarker
import com.example.stochia.ui.theme.NeutralLight
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
        Text("Simulaciones Monte Carlo",
            style = Typography.headlineLarge,
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.innerShadow(
                shape = RoundedCornerShape(20.dp),
                shadow = Shadow(
                    radius = 15.dp,
                    color = NeutralDarker
                ),
            ).fillMaxSize(0.9f)) {

            TextField(
                value = "",
                onValueChange = { /*TODO*/ },
                colors = TextFieldDefaults.colors(
                    disabledContainerColor = NeutralLight,
                    unfocusedContainerColor = NeutralLight,
                    focusedContainerColor = NeutralLight,
                    unfocusedIndicatorColor = NeutralLight,
                    focusedIndicatorColor = NeutralLight,
                ),
                label = { Text("Cantidad de simulaciones") },
                modifier = Modifier.innerShadow(
                    shape = RoundedCornerShape(5.dp),
                    shadow = Shadow(
                        radius = 1.dp,
                        color = NeutralDarker
                    ),
                )
            )

        }
    }

}

