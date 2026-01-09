package com.uncannyvalley.astralvpn.presentation.premium

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class PremiumViewModel : ViewModel() {

    private val _uiState = mutableStateOf<PremiumUiState>(
        PremiumUiState.YearPlan
    )
    val uiState: MutableState<PremiumUiState> = _uiState

    fun onPlanSelected(plan: PremiumUiState) {
        _uiState.value = plan
    }

}