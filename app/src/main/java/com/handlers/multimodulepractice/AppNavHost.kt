package com.handlers.multimodulepractice

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.handlers.auth.splash.authNavGraph
import com.handlers.auth.splash.authRoute

@Composable
fun AppNavHost(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = authRoute
    ) {
        authNavGraph(
            navController = navHostController,
            onAuthSuccess = {

            }
        )
    }
}