package com.example.billeterasimple

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TransaccionDao {
    @Query("SELECT * from transacciones ORDER BY monto ASC")
    fun getAlphabetizedWords(): LiveData<List<Transaccion>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(transaction: Transaccion)

    @Query("DELETE FROM transacciones")
    fun deleteAll()
}