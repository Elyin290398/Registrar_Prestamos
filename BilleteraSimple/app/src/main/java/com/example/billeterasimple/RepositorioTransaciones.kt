package com.example.billeterasimple

import androidx.lifecycle.LiveData

class RepositorioTransaciones(private val transacionDao: TransaccionDao) {

    val allTransacciones: LiveData<List<Transaccion>> = transacionDao.getAlphabetizedWords()

    suspend fun insert(transacion: Transaccion) {
        transacionDao.insert(transacion)
    }
}