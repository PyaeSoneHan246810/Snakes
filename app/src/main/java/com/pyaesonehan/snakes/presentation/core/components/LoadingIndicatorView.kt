package com.pyaesonehan.snakes.presentation.core.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.pyaesonehan.snakes.presentation.theme.SnakesTheme

@Composable
fun LoadingIndicatorView(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Preview
@Composable
private fun LoadingIndicatorViewPreview() {
    SnakesTheme {
        Surface {
            LoadingIndicatorView()
        }
    }
}