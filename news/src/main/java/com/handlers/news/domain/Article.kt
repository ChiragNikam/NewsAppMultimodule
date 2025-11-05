package com.handlers.news.domain

data class Article(
    val title: String,
    val description: String?,
    val imageUrl: String?,
    val url: String,
    val source: String?,
    val publishedAt: String
)