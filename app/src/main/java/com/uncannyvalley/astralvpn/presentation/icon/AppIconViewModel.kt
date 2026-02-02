package com.uncannyvalley.astralvpn.presentation.icon

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uncannyvalley.astralvpn.domain.model.AppIcon
import com.uncannyvalley.astralvpn.domain.repository.AppIconRepository
import com.uncannyvalley.astralvpn.domain.usecase.ChangeAppIconUseCase
import com.uncannyvalley.astralvpn.presentation.settings.mapper.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppIconViewModel @Inject constructor(
    private val changeAppIcon: ChangeAppIconUseCase,
    private val appIconRepository: AppIconRepository
) : ViewModel() {

    init {
        viewModelScope.launch {
            appIconRepository.observeIcon().collect { icon ->
                selectedIcon = icon
            }
        }
    }

    val iconOptions: List<AppIconOption> =
        AppIcon.entries.map { it.toUiModel() }

    var selectedIcon by mutableStateOf(AppIcon.DEFAULT)
        private set

    fun onIconSelected(icon: AppIcon) {
        selectedIcon = icon

        viewModelScope.launch {
            appIconRepository.saveIcon(icon)
            changeAppIcon(icon)
        }
    }
}