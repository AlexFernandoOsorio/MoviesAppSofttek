package com.example.moviesappsofttek.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_account")
data class AccountEntity(
    @PrimaryKey
    @ColumnInfo(name = "ta_id") val id: Int,
    @ColumnInfo(name = "ta_user") val user: String,
    @ColumnInfo(name = "ta_password") val password: String
)