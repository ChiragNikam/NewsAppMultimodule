package com.handlers.news.data

import android.util.Log
import com.handlers.news.data.model.RetrofitProvider
import com.handlers.news.domain.Article
import com.handlers.news.domain.toDomain

class NewsRepository {
    private val api = RetrofitProvider.api
    private val apiKey = "35d25206115c41a0bfce40750593ffe8"

    suspend fun topHeadlines(country: String = "us", page: Int = 1): List<Article> {
        val res = api.topHeadlines(country, page, apiKey = apiKey)
        if (res.status != "ok") error("News API error")
        return res.articles.map { it.toDomain() }
    }

    suspend fun search(query: String, page: Int = 1): List<Article> {
        val res = api.searchEverything(query, page = page, apiKey = apiKey)
        if (res.status != "ok") error("News API error")
        return res.articles.map { it.toDomain() }
    }

    suspend fun testApi() {
        try {
            val res = RetrofitProvider.api.topHeadlinesString(apiKey = "35d25206115c41a0bfce40750593ffe8")
            Log.d("TEST", "Success: $res")
        } catch (e: Exception) {
            Log.e("TEST", "Error: ${e.message}", e)
        }
    }
}