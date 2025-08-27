package com.pyaesonehan.snakes.presentation.snake_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
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
fun SnakeDetailsScreenView(
    modifier: Modifier = Modifier,
    snakeUiState: SnakeDetailsViewModel.Companion.SnakeUiState,
    onRetry: () -> Unit = {},
    onNavigateBack: () -> Unit = {}
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    when(snakeUiState) {
                        is SnakeDetailsViewModel.Companion.SnakeUiState.Loading, SnakeDetailsViewModel.Companion.SnakeUiState.Failure -> {}
                        is SnakeDetailsViewModel.Companion.SnakeUiState.Success -> {
                            val title = snakeUiState.snake?.englishName ?: stringResource(R.string.unavailable)
                            Text(
                                text = title
                            )
                        }
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = onNavigateBack
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowLeft,
                            contentDescription = stringResource(R.string.back)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) { 
            when(snakeUiState) {
                is SnakeDetailsViewModel.Companion.SnakeUiState.Loading -> {
                    LoadingIndicatorView()
                }
                is SnakeDetailsViewModel.Companion.SnakeUiState.Failure -> {
                    FailureView(
                        onRetry = onRetry
                    )
                }
                is SnakeDetailsViewModel.Companion.SnakeUiState.Success -> {
                    SnakeDetailsView(
                        snake = snakeUiState.snake
                    )
                }
            }
        }
    }
}

@Composable
private fun SnakeDetailsView(
    modifier: Modifier = Modifier,
    snake: Snake?
) {
    Column(
        modifier = modifier
            .verticalScroll(
                state = rememberScrollState()
            )
    ) {
        val image = getSnakeImageResId(snake?.id ?: 0)
        val contentDesc = snake?.englishName
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp),
            painter = painterResource(image),
            contentDescription = contentDesc,
            contentScale = ContentScale.Crop
        )
        if (snake != null) {
            val englishName = snake.englishName
            val myanmarName = snake.myanmarName
            val detail = snake.detail
            val isPoisonous = snake.isPoisonousBool
            val isDangerous = snake.isDangerousBool
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            ) {
                Text(
                    text = myanmarName,
                    style = MaterialTheme.typography.headlineSmall
                )
                Spacer(
                    modifier = Modifier
                        .height(4.dp)
                )
                Text(
                    text = englishName,
                    style = MaterialTheme.typography.titleLarge
                )
                if (isPoisonous || isDangerous) {
                    Spacer(
                        modifier = Modifier
                            .height(8.dp)
                    )
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        if (isPoisonous) {
                            LabelChipView(
                                label = stringResource(R.string.poisonous),
                                color = Color.Magenta
                            )
                        }
                        if (isDangerous) {
                            LabelChipView(
                                label = stringResource(R.string.dangerous),
                                color = Color.Red
                            )
                        }
                    }
                }
                Spacer(
                    modifier = Modifier
                        .height(20.dp)
                )
                Text(
                    text = detail,
                    lineHeight = 2.em
                )
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = stringResource(R.string.snake_details_unavailable),
                    style = MaterialTheme.typography.titleMedium
                )
                Button(
                    onClick = {}
                ) {
                    Text(
                        text = stringResource(R.string.back)
                    )
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun SnakesDetailsScreenViewPreview1() {
    SnakesTheme {
        SnakeDetailsScreenView(
            snakeUiState = SnakeDetailsViewModel.Companion.SnakeUiState.Success(
                snake = PreviewData.snakes.firstOrNull()
            )
        )
    }
}

@PreviewLightDark
@Composable
private fun SnakesDetailsScreenViewPreview2() {
    SnakesTheme {
        SnakeDetailsScreenView(
            snakeUiState = SnakeDetailsViewModel.Companion.SnakeUiState.Success(
                snake = null
            )
        )
    }
}