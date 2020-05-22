package com.example.billeterasimple

import androidx.lifecycle.LiveData

class RepositorioTipoTransaccion(private val tipoTransaccionDao: TipoTransaccionDao) {

    val allTipoTransacciones: LiveData<List<TipoTransaccion>> = tipoTransaccionDao.getAlphabetizedWords()

    suspend fun insert(tipoTransaccion: TipoTransaccion) {
        tipoTransaccionDao.insert(tipoTransaccion)
    }

}