package com.handlers.news.domain

import com.handlers.news.data.model.ArticleDto


fun ArticleDto.toDomain(): Article =
    Article(
        title = title ?: "(no title)",
        description = description,
        imageUrl = urlToImage,
        url = url ?: "",
        source = source?.name,
        publishedAt = publishedAt ?: ""
    )