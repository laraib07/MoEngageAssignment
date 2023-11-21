package com.laraib07.moengageassignment.ui.screen

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
class HomeViewModel @Inject constructor(
    private val repository: ArticleRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            repository.fetchArticles()
            repository.getAllArticles.collect { articles ->
                _uiState.update {
                    it.copy(
                        articles = articles,
                        isLoading = false
                    )
                }
            }
        }
    }
}

data class HomeUiState(
    val articles: List<Article> = emptyList(),
    val isLoading: Boolean = true
)