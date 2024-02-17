package com.example.moviesappsofttek.data.mappers

import com.example.moviesappsofttek.data.local.entities.AccountEntity
import com.example.moviesappsofttek.domain.models.login.AccountModel


fun AccountEntity.toDomainAccount(): AccountModel {
    return AccountModel(
        id = this.id,
        user = this.user,
        password = this.password
    )
}

fun AccountModel.toDataAccount(): AccountEntity {
    return AccountEntity(
        id = this.id,
        user = this.user,
        password = this.password
    )
}