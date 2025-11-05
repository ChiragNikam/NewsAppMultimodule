package com.handlers.auth.sign_up.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.userProfileChangeRequest

class SignUpViewModel : ViewModel() {

    var username = mutableStateOf("")
    var email = mutableStateOf("")
    var password = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    private val auth = FirebaseAuth.getInstance()

    fun onUsernameChange(value: String) { username.value = value }
    fun onEmailChange(value: String) { email.value = value }
    fun onPasswordChange(value: String) { password.value = value }

    fun signUp(onSuccess: () -> Unit, onError: (String) -> Unit) {
        isLoading.value = true

        auth.createUserWithEmailAndPassword(email.value, password.value)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    val profileUpdate = userProfileChangeRequest {
                        displayName = username.value
                    }

                    user?.updateProfile(profileUpdate)
                        ?.addOnCompleteListener {
                            isLoading.value = false
                            onSuccess()
                        }

                } else {
                    isLoading.value = false
                    onError(task.exception?.message ?: "Signup failed")
                }
            }
    }
}
