package com.handlers.news

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.handlers.news.presentation.NewsScreen

object NewsRoute {
    const val route = "news"
}

fun NavGraphBuilder.newsNavGraph() {
    composable(NewsRoute.route) { NewsScreen() }
}