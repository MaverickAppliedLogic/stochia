package com.example.stochia.ui.screen.montecarlo_form_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.innerShadow
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.stochia.ui.screen.common_components.CustomDropdownMenu
import com.example.stochia.ui.screen.common_components.CustomEditText
import com.example.stochia.ui.theme.NeutralDarker
import com.example.stochia.ui.theme.NeutralLight
import com.example.stochia.ui.theme.Primary
import com.example.stochia.ui.theme.SecondaryLight
import com.example.stochia.ui.theme.Typography
import com.example.stochia.ui.viewmodel.MainScreenEvent

enum class DistributionType(val label: String) {
    NORMAL("Normal"),
    UNIFORME("Uniforme"),
    BETA("Beta"),
    BERNOULLI("Bernoulli"),
    BINOMIAL("Binomial"),
    EXPONENTIAL("Exponential"),
    POISSON("Poisson"),
    GEOMETRICA("Geométrica"),
    MULTINOMIAL("Multinomial")
}

@Composable
fun MontecarloFormFields(
    type: DistributionType,
    modifier: Modifier = Modifier,
    onClick: (MainScreenEvent) -> Unit
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
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
    )
    {
        Spacer(modifier = Modifier.weight(0.5f))
        Text("TIPO DE DISTRIBUCION",
            color = SecondaryLight,
            textAlign = TextAlign.Start,
            style = Typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth(0.65f)
        )
        Spacer(modifier = Modifier.weight(0.05f))
        CustomDropdownMenu(
            options = DistributionType.entries.map { it.label },
            modifier = Modifier,
            onClick = {onClick(it)}
        )
        Spacer(modifier = Modifier.weight(0.5f))
        when (type) {
            DistributionType.NORMAL,
            DistributionType.UNIFORME,
            DistributionType.BETA,
            DistributionType.BERNOULLI,
            DistributionType.BINOMIAL -> {
                TwoParamsFieldsWithSize(Modifier.weight(0.2f))
            }

            DistributionType.EXPONENTIAL,
            DistributionType.POISSON -> {
                OneParamFieldsWithSize(Modifier.weight(0.2f))
            }

            DistributionType.GEOMETRICA -> {
                OneParamFields(Modifier.weight(0.2f))
            }

            DistributionType.MULTINOMIAL -> {
                OneParamFieldsWithSize(Modifier.weight(0.2f))
            }
        }

        Spacer(modifier.weight(1f))
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
}

@Composable
fun TwoParamsFieldsWithSize(
    modifier: Modifier = Modifier
){
    Text("PARAMETRO 1",
        color = SecondaryLight,
        textAlign = TextAlign.Start,
        style = Typography.bodyLarge,
        modifier = Modifier
            .fillMaxWidth(0.65f)
    )
    Spacer(modifier = modifier)
    CustomEditText()
    Spacer(modifier = modifier)
    Spacer(modifier = modifier)
    Text("PARAMETRO 2",
        color = SecondaryLight,
        textAlign = TextAlign.Start,
        style = Typography.bodyLarge,
        modifier = Modifier
            .fillMaxWidth(0.65f)
    )
    Spacer(modifier = modifier)
    CustomEditText()
    Spacer(modifier = modifier)
    Spacer(modifier = modifier)
    Text(
        "NUMERO DE INTERACCIONES (N)",
        color = SecondaryLight,
        textAlign = TextAlign.Start,
        style = Typography.bodyLarge,
        modifier = Modifier
            .fillMaxWidth(0.65f)

    )
    Spacer(modifier = modifier)
    CustomEditText()
}

@Composable
fun OneParamFieldsWithSize(
    modifier: Modifier = Modifier
){
    Text("PARAMETRO 1",
        color = SecondaryLight,
        textAlign = TextAlign.Start,
        style = Typography.bodyLarge,
        modifier = Modifier
            .fillMaxWidth(0.65f)
    )
    Spacer(modifier = modifier)
    CustomEditText()
    Spacer(modifier = modifier)
    Spacer(modifier = modifier)
    Text(
        "NUMERO DE INTERACCIONES (N)",
        color = SecondaryLight,
        textAlign = TextAlign.Start,
        style = Typography.bodyLarge,
        modifier = Modifier
            .fillMaxWidth(0.65f)

    )
    Spacer(modifier = modifier)
    CustomEditText()
}

@Composable
fun OneParamFields(
    modifier: Modifier = Modifier
){
    Text("PARAMETRO 1",
        color = SecondaryLight,
        textAlign = TextAlign.Start,
        style = Typography.bodyLarge,
        modifier = Modifier
            .fillMaxWidth(0.65f)
    )
    Spacer(modifier = modifier)
    CustomEditText()
}