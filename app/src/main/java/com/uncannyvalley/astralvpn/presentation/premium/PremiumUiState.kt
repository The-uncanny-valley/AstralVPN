package com.uncannyvalley.astralvpn.presentation.premium

sealed class PremiumUiState {
    data object YearPlan : PremiumUiState()
    data object MonthPlan : PremiumUiState()
}