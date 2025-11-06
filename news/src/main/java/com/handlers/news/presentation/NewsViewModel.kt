package com.handlers.news.presentation

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.handlers.news.data.NewsRepository
import com.handlers.news.domain.Article
import kotlinx.coroutines.launch

class NewsViewModel(
    private val repo: NewsRepository = NewsRepository()
): ViewModel() {

    val query = mutableStateOf("")
    val isLoading = mutableStateOf(false)
    val error = mutableStateOf<String?>(null)
    val items = mutableStateOf<List<Article>>(emptyList())

    fun loadHeadlines(country: String = "Us") {
        viewModelScope.launch {
            isLoading.value = true; error.value = null
            try { items.value = repo.topHeadlines(country) }
            catch (e: Exception) {
                error.value = e.message
                Log.e("NewsViewModel", "Error loading headlines", e)
            }
            finally { isLoading.value = false }
        }
    }

    fun search() {
        val q = query.value.trim()
        if (q.isEmpty()) { loadHeadlines(); return }
        viewModelScope.launch {
            isLoading.value = true; error.value = null
            try { items.value = repo.search(q) }
            catch (e: Exception) { error.value = e.message }
            finally { isLoading.value = false }
        }
    }
}