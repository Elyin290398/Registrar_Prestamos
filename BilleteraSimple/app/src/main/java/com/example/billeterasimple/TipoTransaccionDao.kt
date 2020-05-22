package com.example.billeterasimple

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TipoTransaccionDao {

    @Query("SELECT * from tipos_transacciones")
    fun getAlphabetizedWords(): LiveData<List<TipoTransaccion>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(tipo: TipoTransaccion)

    @Query("DELETE FROM tipos_transacciones")
    suspend fun deleteAll()
}