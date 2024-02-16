package com.example.moviesappsofttek.domain.models.login

import com.example.moviesappsofttek.core.utils.ResourceEvent


data class LoginResultModel(
    val passwordError: String? = null,
    val userError: String? = null,
    val result: ResourceEvent<Unit>? = null
)