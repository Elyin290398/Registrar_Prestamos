package com.example.billeterasimple

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "tipos_transacciones"
)
class TipoTransaccion(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "tipo") var tipo: String
)