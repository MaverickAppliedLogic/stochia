package com.example.stochia.ui.screen.common_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.innerShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.stochia.ui.theme.NeutralDarkest
import com.example.stochia.ui.theme.NeutralLight

@Composable
fun CustomEditText(
    value: String,
    modifier: Modifier = Modifier,
    onValueChange : (String) -> Unit
){
    var text by remember{ mutableStateOf(value) }
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = modifier
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
            value = text,
            onValueChange = {
                text = it
                onValueChange(it)
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = TextFieldDefaults.colors(
                disabledContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
            ),
            label = {  },
            modifier = Modifier.padding(bottom = 15.dp)
        )
    }
}