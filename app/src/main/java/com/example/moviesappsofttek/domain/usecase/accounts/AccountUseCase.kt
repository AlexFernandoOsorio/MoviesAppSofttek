package com.example.moviesappsofttek.domain.usecase.accounts

import com.example.moviesappsofttek.domain.models.login.AccountModel
import com.example.moviesappsofttek.domain.models.login.LoginResultModel
import com.example.moviesappsofttek.domain.repositories.AccountRepository
import javax.inject.Inject

class AccountUseCase @Inject constructor(
    private val accountRepository: AccountRepository
){

    suspend operator fun invoke(id : Int, username: String, password: String): LoginResultModel {
        val usuarioError = if (username.isBlank()) "El usuario no puede estar vacio" else null
        val passwordError = if (password.isBlank()) "El passwword no puede estar vacio" else null
        //Se valida que el email y el password no estén vacíos
        if (usuarioError != null) {
            return LoginResultModel(
                userError = usuarioError
            )
        }
        if (passwordError != null) {
            return LoginResultModel(
                passwordError = passwordError
            )
        }
        //Se retorna el resultado de la petición realizando una llamada al repositorio
        return LoginResultModel(
            result = accountRepository.getAccountFromLocal(id,username, password)
        )
    }

    suspend fun saveAccountToLocal(username: String, password: String) {
        val account = AccountModel(1,username, password)
        accountRepository.saveAccountToLocal(account)
    }
}