package com.example.moviesappsofttek.domain.models.login

import com.example.moviesappsofttek.core.utils.FlowResult


data class LoginResultModel(
    val passwordError: String? = null,
    val userError: String? = null,
    val result: FlowResult<Unit>? = null
)