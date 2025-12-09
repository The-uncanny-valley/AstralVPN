package com.uncannyvalley.astralvpn.presentation.profile

sealed class ProfileUiState {
    data class Loaded(
        val userName: String,
        val isPremium: Boolean
    ) : ProfileUiState()

    data object Loading : ProfileUiState()
    data object NoInternet : ProfileUiState()
    data object NoData : ProfileUiState()
}