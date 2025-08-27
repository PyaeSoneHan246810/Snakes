package com.pyaesonehan.snakes.presentation.snakes

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.pyaesonehan.snakes.R
import com.pyaesonehan.snakes.data.utils.PreviewData
import com.pyaesonehan.snakes.domin.model.Snake
import com.pyaesonehan.snakes.domin.model.isDangerousBool
import com.pyaesonehan.snakes.domin.model.isPoisonousBool
import com.pyaesonehan.snakes.presentation.core.components.FailureView
import com.pyaesonehan.snakes.presentation.core.components.LabelChipView
import com.pyaesonehan.snakes.presentation.core.components.LoadingIndicatorView
import com.pyaesonehan.snakes.presentation.core.utils.getSnakeImageResId
import com.pyaesonehan.snakes.presentation.theme.SnakesTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SnakesScreenView(
    modifier: Modifier = Modifier,
    snakesUiState: SnakesViewModel.Companion.SnakesUiState,
    onRetry: () -> Unit = {},
    onSnakeClick: (Int) -> Unit = {}
) {
    val topAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .nestedScroll(topAppBarScrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.app_name)
                    )
                },
                scrollBehavior = topAppBarScrollBehavior
            )
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when(snakesUiState) {
                is SnakesViewModel.Companion.SnakesUiState.Loading -> {
                    LoadingIndicatorView()
                }
                is SnakesViewModel.Companion.SnakesUiState.Failure -> {
                    FailureView(
                        onRetry = onRetry
                    )
                }
                is SnakesViewModel.Companion.SnakesUiState.Success -> {
                    val snakes = snakesUiState.snakes
                    SnakesListView(
                        snakes = snakes,
                        onSnakeClick = onSnakeClick
                    )
                }
            }
        }
    }
}

@Composable
private fun SnakesListView(
    modifier: Modifier = Modifier,
    snakes: List<Snake>,
    onSnakeClick: (Int) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(
            items = snakes,
            key = { it.id }
        ) { snake ->
            SnakeCardView(
                snake = snake,
                onClick = {
                    onSnakeClick(snake.id)
                }
            )
        }
    }
}

@Composable
private fun SnakeCardView(
    modifier: Modifier = Modifier,
    snake: Snake,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    onClick = onClick
                ),
            verticalAlignment = Alignment.Top
        ) {
            Image(
                modifier = Modifier
                    .size(120.dp),
                painter = painterResource(getSnakeImageResId(snake.id)),
                contentDescription = snake.englishName,
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                Text(
                    text = snake.myanmarName,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = snake.englishName,
                    style = MaterialTheme.typography.titleSmall
                )
                Spacer(
                    modifier = Modifier.height(8.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    if (snake.isPoisonousBool) {
                        LabelChipView(
                            label = stringResource(R.string.poisonous),
                            color = Color.Magenta
                        )
                    }
                    if (snake.isDangerousBool) {
                        LabelChipView(
                            label = stringResource(R.string.dangerous),
                            color = Color.Red
                        )
                    }
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun SnakesScreenViewPreview() {
    SnakesTheme {
        SnakesScreenView(
            snakesUiState = SnakesViewModel.Companion.SnakesUiState.Success(
                snakes = PreviewData.snakes
            )
        )
    }
}