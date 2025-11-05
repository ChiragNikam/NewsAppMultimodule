package com.handlers.auth

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.handlers.auth.sign_up.presentation.SignUpScreen
import com.handlers.auth.splash.LoginScreen
import com.handlers.auth.splash.SplashScreen

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
            SplashScreen(navController) {
                onAuthSuccess()
            }
        }

        composable(AuthScreen.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    onAuthSuccess()
                },
                onSignUpClick = {
                    navController.navigate(AuthScreen.SignUp.route)
                }
            )
        }

        composable(AuthScreen.SignUp.route) {
            SignUpScreen(
                onSignUpSuccess = {
                    onAuthSuccess()
                },
                onLoginClick = {
                    navController.navigate(AuthScreen.Login.route)
                }
            )
        }
    }
}