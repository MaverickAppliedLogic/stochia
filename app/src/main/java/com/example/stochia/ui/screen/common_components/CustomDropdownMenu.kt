package com.example.stochia.ui.screen.common_components

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuItemColors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.dp
import com.example.stochia.ui.screen.montecarlo_form_components.DistributionType
import com.example.stochia.ui.theme.Neutral
import com.example.stochia.ui.theme.NeutralDarker
import com.example.stochia.ui.theme.NeutralLight
import com.example.stochia.ui.theme.SecondaryDarkest
import com.example.stochia.ui.theme.Typography
import com.example.stochia.ui.viewmodel.MainScreenEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDropdownMenu(
    selected: String,
    options: List<String>,
    modifier: Modifier = Modifier,
    onClick: (MainScreenEvent) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(Neutral)
            .innerShadow(
            shape = RoundedCornerShape(10.dp),
            shadow = Shadow(
                radius = 10.dp,
                spread = 5.dp,
                color = NeutralDarker
            )
        )
    ) {
        OutlinedTextField(
            value =  selected,
            textStyle = Typography.bodyLarge,
            onValueChange = { },
            trailingIcon = {
                Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = "",
                    tint = SecondaryDarkest)
            },
            singleLine = true,
            readOnly = true,
            modifier = modifier.menuAnchor(),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                errorTrailingIconColor = Color.Red,
                errorContainerColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
            )
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = modifier
                .clip(
                    shape = RoundedCornerShape(5.dp)
                )
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = option.first()+option.substring(1).lowercase(),
                            color = NeutralLight,
                        )
                    },
                    onClick = {
                        expanded = !expanded
                        onClick(
                            MainScreenEvent
                                .ChangedDistributionType(DistributionType.valueOf(option))
                        )
                              },
                    colors = MenuItemColors(
                        textColor = SecondaryDarkest,
                        disabledTextColor = SecondaryDarkest,
                        leadingIconColor = Color.Transparent,
                        trailingIconColor = Color.Transparent,
                        disabledLeadingIconColor = Color.Transparent,
                        disabledTrailingIconColor = Color.Transparent
                    )
                )
            }
        }
    }
}