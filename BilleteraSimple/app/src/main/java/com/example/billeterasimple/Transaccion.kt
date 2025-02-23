package com.example.billeterasimple

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "transacciones"
)

data class Transaccion(
    @PrimaryKey(autoGenerate= true)
    var id: Int= 0,
    @ColumnInfo(name="tipo_transaccion_id") var tipoTransaccionId: Int,
    @ColumnInfo(name="monto") var monto: String
)
