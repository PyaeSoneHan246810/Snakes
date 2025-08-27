package com.pyaesonehan.snakes.presentation.core.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pyaesonehan.snakes.R
import com.pyaesonehan.snakes.presentation.theme.SnakesTheme

@Composable
fun LabelChipView(
    modifier: Modifier = Modifier,
    label: String,
    color: Color
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(100),
        color = color.copy(
            alpha = 0.15f
        )
    ) {
        Text(
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 12.dp),
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Preview
@Composable
private fun LabelChipViewPreview() {
    SnakesTheme {
        Surface {
            LabelChipView(
                label = stringResource(R.string.dangerous),
                color = Color.Red
            )
        }
    }
}