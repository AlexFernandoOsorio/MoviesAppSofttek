package com.example.moviesappsofttek.domain.repositories

import com.example.moviesappsofttek.core.utils.FlowResult
import com.example.moviesappsofttek.domain.models.login.AccountModel

interface AccountRepository {
    //suspend fun getAccountFromRemote(username: String, password: String): ResourceEvent<Unit>

    suspend fun getAccountFromLocal(id : Int, username : String, password : String): FlowResult<Unit>

    suspend fun saveAccountToLocal(account: AccountModel)
}