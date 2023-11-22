package com.laraib07.moengageassignment.ui.screen

import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage

@Composable
fun NewsScreen(
    modifier: Modifier = Modifier,
    viewModel: NewsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState(initial = NewsUiState())

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
            .verticalScroll(rememberScrollState())
    ) {
        AsyncImage(
            model = uiState.article?.urlToImg,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .fillMaxWidth(),
            alignment = Alignment.Center
        )
        Text(text = uiState.article?.title ?: "N/A")
        Text(text = uiState.article?.content ?: "N/A")
    }
}

@Preview(showBackground = true)
@Composable
fun NewsScreenPrev() {
    NewsScreen()
}