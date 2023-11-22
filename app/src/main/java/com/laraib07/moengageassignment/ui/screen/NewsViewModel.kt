package com.laraib07.moengageassignment.ui.screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laraib07.moengageassignment.data.model.Article
import com.laraib07.moengageassignment.data.repository.ArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: ArticleRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(NewsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        val url: String = checkNotNull(savedStateHandle["url"])
        viewModelScope.launch {
            repository.getArticleByURL(url).collect { article ->
                _uiState.update {
                    it.copy(
                        article = article,
                    )
                }
            }
        }
    }
}

data class NewsUiState(
    val article: Article? = null
)