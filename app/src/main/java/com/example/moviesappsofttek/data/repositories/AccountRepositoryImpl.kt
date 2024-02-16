package com.example.moviesappsofttek.data.repositories

import com.example.moviesappsofttek.core.utils.GlobalConstants.errorNoUser
import com.example.moviesappsofttek.core.utils.ResourceEvent
import com.example.moviesappsofttek.data.local.dao.DatabaseDao
import com.example.moviesappsofttek.data.remote.mappers.toDataAccount
import com.example.moviesappsofttek.data.remote.mappers.toDomainAccount
import com.example.moviesappsofttek.domain.models.login.AccountModel
import com.example.moviesappsofttek.domain.repositories.AccountRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val accountDao: DatabaseDao
) : AccountRepository {

    override suspend fun getAccountFromLocal(
        id: Int,
        username: String,
        password: String
    ): ResourceEvent<Unit> {
        return try {
            //Recuperamos el sealed class del evento de la petición a la API
            val response = accountDao.getAccountById(id, username, password)?.toDomainAccount()
            //Retornamos un ResourceEvent.Success
            response?.let { ResourceEvent.Success(Unit) }
                ?: ResourceEvent.Error(errorNoUser)
        } catch (e: IOException) {
            //En caso de que la petición no sea exitosa se retorna un ResourceEvent.Error
            ResourceEvent.Error(errorNoUser)
        } catch (e: HttpException) {
            //En caso de que la petición no sea exitosa se retorna un ResourceEvent.Error
            ResourceEvent.Error(errorNoUser)
        }
    }

    override suspend fun saveAccountToLocal(
        account: AccountModel
    ) {
        accountDao.insertAccount(account.toDataAccount())
    }

}