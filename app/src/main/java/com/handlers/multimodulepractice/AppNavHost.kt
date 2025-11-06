package com.handlers.multimodulepractice

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.handlers.auth.authNavGraph
import com.handlers.auth.authRoute
import com.handlers.news.NewsRoute
import com.handlers.news.newsNavGraph

@Composable
fun AppNavHost(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = authRoute
    ) {
        authNavGraph(
            navController = navHostController,
            onAuthSuccess = {
                navHostController.navigate(NewsRoute.route) {
                    popUpTo(authRoute) { inclusive = true }
                }
            }
        )

        newsNavGraph(navController = navHostController) {
            navHostController.navigate(authRoute) {
                popUpTo(NewsRoute.route) { inclusive = true }
            }
        }
    }
}