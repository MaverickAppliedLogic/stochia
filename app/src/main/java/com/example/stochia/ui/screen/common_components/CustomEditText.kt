package com.example.stochia.ui.screen.common_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.innerShadow
import androidx.compose.ui.graphics.SolidColor
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

    // BasicTextField elimina completamente el contentPadding de 16dp que impone
    // Material3 TextField, que en campos de altura pequeña (HDPI) descentraba
    // y recortaba el texto. El decorationBox centra el contenido manualmente.
    BasicTextField(
        value = text,
        onValueChange = {
            text = it
            onValueChange(it)
        },
        maxLines = maxLines,
        textStyle = MaterialTheme.typography.bodyLarge,
        keyboardOptions = KeyboardOptions(keyboardType = type),
        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
        decorationBox = { innerTextField ->
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = dimens.cardInnerPadding)
            ) {
                innerTextField()
            }
        },
        modifier = modifier
            .clip(RoundedCornerShape(dimens.cornerRadiusSmall))
            .background(NeutralLight)
            .innerShadow(
                shape = RoundedCornerShape(dimens.cornerRadiusSmall),
                shadow = Shadow(
                    radius = dimens.editTextShadowRadius,
                    spread = dimens.editTextShadowSpread,
                    color = NeutralDarkest
                )
            )
    )
}
