package com.example.stochia.ui.screen.main_screen_components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Memory
import androidx.compose.material.icons.outlined.PhoneAndroid
import androidx.compose.material.icons.outlined.Cloud
import androidx.compose.material.icons.outlined.Sync
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.stochia.domain.model.engineMode.EngineMode
import com.example.stochia.ui.theme.LocalDimens
import com.example.stochia.ui.theme.Neutral
import com.example.stochia.ui.theme.NeutralLight
import com.example.stochia.ui.theme.PrimaryLight
import com.example.stochia.ui.theme.Secondary
import com.example.stochia.ui.viewmodel.MainScreenEvent

private data class EngineModeOption(
    val mode: EngineMode,
    val label: String,
    val icon: ImageVector
)

private val engineOptions = listOf(
    EngineModeOption(EngineMode.LOCAL,   "Local",    Icons.Outlined.PhoneAndroid),
    EngineModeOption(EngineMode.REMOTE,  "Remoto",   Icons.Outlined.Cloud),
    EngineModeOption(EngineMode.DYNAMIC, "Dinámico", Icons.Outlined.Sync),
)

private fun iconForMode(mode: EngineMode): ImageVector = when (mode) {
    EngineMode.LOCAL   -> Icons.Outlined.PhoneAndroid
    EngineMode.REMOTE  -> Icons.Outlined.Cloud
    EngineMode.DYNAMIC -> Icons.Outlined.Sync
}

@Composable
fun EngineButton(
    engineMode: EngineMode,
    menuVisible: Boolean,
    modifier: Modifier = Modifier,
    onClick: (MainScreenEvent) -> Unit
) {
    val dimens = LocalDimens.current

    Box(modifier = modifier) {
        IconButton(
            onClick = { onClick(MainScreenEvent.EngineButtonClicked) },
            colors = IconButtonDefaults.iconButtonColors(contentColor = PrimaryLight),
            modifier = Modifier.clip(RoundedCornerShape(dimens.cornerRadiusMedium))
        ) {
            Icon(
                imageVector = iconForMode(engineMode),
                contentDescription = "Motor de cálculo",
                tint = PrimaryLight
            )
        }

        DropdownMenu(
            expanded = menuVisible,
            onDismissRequest = { onClick(MainScreenEvent.EngineMenuDismissed) },
            containerColor = NeutralLight,
        ) {
            engineOptions.forEach { option ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = option.label,
                            color = if (option.mode == engineMode) PrimaryLight else Secondary
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = option.icon,
                            contentDescription = null,
                            tint = if (option.mode == engineMode) PrimaryLight else Secondary
                        )
                    },
                    onClick = { onClick(MainScreenEvent.EngineSelected(option.mode)) },
                    colors = MenuDefaults.itemColors(
                        textColor = Secondary,
                        leadingIconColor = Secondary,
                    )
                )
            }
        }
    }
}
