package com.example.stochia.ui.screen.main_screen_components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.VectorConverter
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.innerShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.stochia.ui.theme.NeutralDarkest
import com.example.stochia.ui.theme.PrimaryLight
import com.example.stochia.ui.theme.Secondary
import com.example.stochia.ui.theme.Typography
import com.example.stochia.ui.viewmodel.MainScreenEvent
import com.example.stochia.ui.viewmodel.Screen


@Composable
fun BottomBarButton(
    currentScreen: Screen,
    type: Screen,
    onClick: (MainScreenEvent) -> Unit
) {
    val selected = currentScreen == type
    val sizeAnim = remember { Animatable(0.dp, Dp.VectorConverter) }
    val floatAnim = remember { Animatable(0.4f) }

    LaunchedEffect(selected) {
        floatAnim.animateTo(
            targetValue = if (selected) 0.5f else 0.6f
        )
        sizeAnim.animateTo(
            targetValue = if (selected) 7.dp else 0.dp
        )
    }

    val animatedSize = sizeAnim.value
    val animatedFloat = floatAnim.value
    val tint =
        if (selected) PrimaryLight
        else Secondary

    IconButton(
        onClick = { onClick(MainScreenEvent.ScreenButtonClicked(type)) },
        colors = IconButtonDefaults
            .iconButtonColors(containerColor = Color.Transparent),
        modifier = Modifier
            .height(80.dp)
            .width(100.dp)
            .clip(RoundedCornerShape(10.dp))
            .innerShadow(
                shape = RoundedCornerShape(9.dp),
                shadow = Shadow(
                    radius = animatedSize,
                    spread = maxOf(0.0, animatedSize.value.toInt() -3.5).dp,
                    color = NeutralDarkest
                ),
            )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Box(modifier = Modifier.fillMaxSize(animatedFloat)) {
                Icon(
                    Icons.Default.AccountBox,
                    contentDescription = "Markov",
                    tint = tint,
                    modifier = Modifier.fillMaxSize()
                )
                Icon(
                    Icons.Default.AccountBox,
                    contentDescription = "Markov",
                    tint = tint,
                    modifier = Modifier
                        .alpha(animatedSize.value)
                        .fillMaxSize()
                        .blur(4.dp)
                )

            }
            Text(
                text = type.name.first() + type.name.substring(1).lowercase(),
                color = tint,
                style = Typography.labelSmall
            )
        }

    }


}