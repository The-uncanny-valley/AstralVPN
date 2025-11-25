package com.uncannyvalley.astralvpn.presentation.home

sealed class HomeUiState {
    data object Normal : HomeUiState()
    data object NoInternet : HomeUiState()
    data object Connecting : HomeUiState()
    data object Connected : HomeUiState()
    data class Error(
        val message: String
    ) : HomeUiState()
}