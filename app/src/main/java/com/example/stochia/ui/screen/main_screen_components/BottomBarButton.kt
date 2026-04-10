package com.example.stochia.ui.screen.main_screen_components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.VectorConverter
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.innerShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.stochia.ui.theme.PrimaryLight
import com.example.stochia.ui.theme.Secondary
import com.example.stochia.ui.theme.Typography
import com.example.stochia.ui.viewmodel.Screen

enum class BarButtonType {
    DISTRIBUTION,
    MONTECARLO,
    MARKOV
}

@Composable
fun BottomBarButton(
    current: Screen,
    type: BarButtonType,
    onClick: () -> Unit
) {
    val selected = current.name == type.name
    val sizeAnim = remember { Animatable(0.dp, Dp.VectorConverter) }
    val floatAnim = remember { Animatable(0.4f) }

    LaunchedEffect(selected) {
        floatAnim.animateTo(
            targetValue = if (selected) 0.6f else 0.7f
        )
        sizeAnim.animateTo(
            targetValue = if (selected) 5.dp else 0.dp
        )
    }

    val animatedSize = sizeAnim.value
    val animatedFloat = floatAnim.value
    val tint =
        if (current.name == type.name) PrimaryLight
        else Secondary

    IconButton(
        onClick = { onClick() },
        colors = IconButtonDefaults
            .iconButtonColors(containerColor = Color.Transparent),
        modifier = Modifier
            .height(80.dp)
            .width(100.dp)
            .clip(RoundedCornerShape(10.dp))
            .innerShadow(
                shape = RoundedCornerShape(9.dp),
                shadow = Shadow(radius = animatedSize, color = PrimaryLight),
            )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Icon(
                Icons.Default.AccountBox,
                contentDescription = "Markov",
                tint = tint,
                modifier = Modifier.fillMaxSize(animatedFloat)
            )
            Text(
                "Markov",
                color = tint,
                style = Typography.labelSmall
            )
        }

    }


}