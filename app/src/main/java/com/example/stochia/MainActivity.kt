package com.example.stochia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.toArgb
import com.example.stochia.ui.screen.MainScreen
import com.example.stochia.ui.theme.NeutralDark
import com.example.stochia.ui.theme.StochiaTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(NeutralDark.toArgb()),
            navigationBarStyle = SystemBarStyle.dark(NeutralDark.toArgb())
        )
        setContent {
            StochiaTheme {
                MainScreen()
            }
        }
    }
}


