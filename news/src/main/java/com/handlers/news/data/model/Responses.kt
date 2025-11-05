package com.handlers.news.data.model

data class NewsTopHeadlinesResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<ArticleDto>
)

data class NewsEverythingResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<ArticleDto>
)
