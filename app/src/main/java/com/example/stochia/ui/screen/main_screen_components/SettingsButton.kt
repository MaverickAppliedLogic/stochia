package com.example.stochia.ui.screen.main_screen_components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.VectorConverter
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.innerShadow
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.stochia.ui.theme.NeutralDarkest
import com.example.stochia.ui.theme.PrimaryLight
import com.example.stochia.ui.viewmodel.MainScreenEvent

@Composable
fun SettingsButton(
    selected: Boolean,
    modifier: Modifier = Modifier,
    onClick: (MainScreenEvent) -> Unit
){
    val sizeAnim = remember { Animatable(0.dp, Dp.VectorConverter) }
    val floatAnim = remember { Animatable(0.4f) }

    LaunchedEffect(selected) {
        floatAnim.animateTo(
            targetValue = if (selected) 0.5f else 0.6f
        )
        sizeAnim.animateTo(
            targetValue = if (selected) 5.dp else 0.dp
        )
    }

    val animatedSize = sizeAnim.value
    val animatedFloat = floatAnim.value

    IconButton(
        onClick = { onClick(MainScreenEvent.SettingsButtonClicked) },
        colors = IconButtonDefaults.iconButtonColors(contentColor = PrimaryLight),
        modifier = modifier
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
        Box(modifier = Modifier.fillMaxSize(animatedFloat)) {
            Icon(
                Icons.Outlined.Settings,
                contentDescription = "Markov",
                tint = PrimaryLight,
                modifier = Modifier.fillMaxSize()
            )
            Icon(
                Icons.Outlined.Settings,
                contentDescription = "Markov",
                tint = PrimaryLight,
                modifier = Modifier
                    .alpha(animatedSize.value)
                    .fillMaxSize()
                    .blur(3.dp)
            )

        }
    }
}