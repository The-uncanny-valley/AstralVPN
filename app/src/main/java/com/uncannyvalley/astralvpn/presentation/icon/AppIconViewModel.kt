package com.uncannyvalley.astralvpn.presentation.icon

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.uncannyvalley.astralvpn.domain.model.AppIcon
import com.uncannyvalley.astralvpn.presentation.settings.mapper.toUiModel

class AppIconViewModel : ViewModel() {

    val iconOptions: List<AppIconOption> =
        AppIcon.entries.map { it.toUiModel() }

    var selectedIconId by mutableStateOf(AppIcon.DEFAULT.name)
        private set

    fun onIconSelected(id: String) {
        selectedIconId = id
    }
}