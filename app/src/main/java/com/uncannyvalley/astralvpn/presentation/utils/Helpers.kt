package com.uncannyvalley.astralvpn.presentation.utils

fun isValidEmail(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}