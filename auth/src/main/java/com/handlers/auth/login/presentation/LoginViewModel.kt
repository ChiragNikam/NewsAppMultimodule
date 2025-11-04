package com.handlers.auth.login.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.handlers.auth.data.repository.UserRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel() : ViewModel() {

    var email = mutableStateOf("")
    var password = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    fun onEmailChange(newEmail: String) {
        email.value = newEmail
    }

    fun onPasswordChange(newPassword: String) {
        password.value = newPassword
    }

    fun login(userRepository: UserRepository, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            isLoading.value = true
            delay(1000) // simulate API delay

            val isValid = userRepository.isValidUser(email.value, password.value)
            isLoading.value = false

            if (isValid) {
                onSuccess()
            } else {
                onError("Invalid credentials")
            }
        }
    }
}