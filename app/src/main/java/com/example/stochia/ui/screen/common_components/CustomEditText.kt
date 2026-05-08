package com.example.stochia.ui.screen.common_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.innerShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.text.input.KeyboardType
import com.example.stochia.ui.theme.LocalDimens
import com.example.stochia.ui.theme.NeutralDarkest
import com.example.stochia.ui.theme.NeutralLight

@Composable
fun CustomEditText(
    modifier: Modifier = Modifier,
    value: String,
    maxLines: Int = Int.MAX_VALUE,
    type: KeyboardType = KeyboardType.Number,
    onValueChange: (String) -> Unit
) {
    val dimens = LocalDimens.current
    var text by remember { mutableStateOf(value) }

    TextField(
        value = text,
        onValueChange = {
            text = it
            onValueChange(it)
        },
        maxLines = maxLines,
        // Sin label: evita que M3 reserve espacio de animación del floating label,
        // lo que descentraba y recortaba el texto en pantallas HDPI con alturas pequeñas.
        textStyle = MaterialTheme.typography.bodyLarge,
        keyboardOptions = KeyboardOptions(keyboardType = type),
        colors = TextFieldDefaults.colors(
            disabledContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
        ),
        modifier = modifier
            .clip(RoundedCornerShape(dimens.cornerRadiusSmall))
            .background(color = NeutralLight)
            .innerShadow(
                shape = RoundedCornerShape(dimens.cornerRadiusSmall),
                shadow = Shadow(
                    radius = dimens.editTextShadowRadius,
                    spread = dimens.editTextShadowSpread,
                    color = NeutralDarkest
                )
            )
            .fillMaxSize()
    )
}
