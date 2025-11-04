package com.uncannyvalley.astralvpn.domain.model

data class ConfigId(val value: String) {
    init { require(value.isNotBlank())}
}
