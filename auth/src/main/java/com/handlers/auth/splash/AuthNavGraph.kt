package com.handlers.auth.splash

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

const val authRoute = "auth"

sealed class AuthScreen(val route: String) {
    data object Splash : AuthScreen("$authRoute/splash")
    data object Login : AuthScreen("$authRoute/login")
    data object SignUp : AuthScreen("$authRoute/signUp")
}

fun NavGraphBuilder.authNavGraph(
    navController: NavController,
    onAuthSuccess: () -> Unit
) {
    navigation(
        startDestination = AuthScreen.Splash.route,
        route = authRoute
    ) {
        composable(AuthScreen.Splash.route) {
            SplashScreen()
        }
    }
}