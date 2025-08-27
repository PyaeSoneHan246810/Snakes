package com.pyaesonehan.snakes.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.pyaesonehan.snakes.presentation.navhost.NavHostView
import com.pyaesonehan.snakes.presentation.theme.SnakesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SnakesTheme {
                NavHostView()
            }
        }
    }
}