package com.handlers.auth.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.compose.AppTheme
import com.handlers.auth.AuthScreen
import com.handlers.theme.R
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavController) {
    // This will trigger once when the composable enters the composition
    LaunchedEffect(Unit) {
        delay(2000) // wait for 2 seconds
        navController.navigate(AuthScreen.Login.route) { // or your next screen
            popUpTo(AuthScreen.Splash.route) { inclusive = true }
        }
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box {
                Image(
                    contentDescription = "Splash Screen",
                    painter = painterResource(R.drawable.ic_launcher_background)
                )
                Image(
                    painter = painterResource(R.drawable.ic_launcher_foreground),
                    contentDescription = "Splash Screen"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    AppTheme {
        SplashScreen(rememberNavController())
    }
}