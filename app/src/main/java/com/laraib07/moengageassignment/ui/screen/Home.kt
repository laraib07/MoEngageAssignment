package com.laraib07.moengageassignment.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.transform.RoundedCornersTransformation
import com.laraib07.moengageassignment.data.model.Article
import com.laraib07.moengageassignment.ui.navigation.Screen

@Composable
fun HomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState(initial = HomeUiState())
    LazyColumn(modifier = modifier) {
        items(uiState.articles) {article ->
            ArticleItem(article = article, onTap = {
                navController.navigate(Screen.NewsScreen.route + "?url=${article.url}")
            })
        }
    }
}

@Composable
fun ArticleItem(
    article: Article,
    onTap: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .clickable {
                       onTap()
            },
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(8.dp)
        ) {
            AsyncImage(
                model = article.urlToImg,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(end = 16.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .size(100.dp),
                alignment = Alignment.Center
            )
            Column(modifier = Modifier.wrapContentWidth()) {

                Text(
                    text = article.title ?: "Title N/A",
                    fontWeight = FontWeight.Bold,
                    softWrap = true
                )
                Text(
                    text = article.sourceName ?: "Source N/A",
                    fontStyle = FontStyle.Italic
                )
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPrev() {
    HomeScreen(rememberNavController())
}