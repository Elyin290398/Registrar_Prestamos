package com.example.billeterasimple

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TransaccionViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: RepositorioTransaciones

    val allTransacciones: LiveData<List<Transaccion>>

    init {
        val transacionesDao = TransaccionRoomDatabase.getDatabase(application, viewModelScope).transaccionDao()
        repository = RepositorioTransaciones(transacionesDao)
        allTransacciones = repository.allTransacciones
    }

    /**
     * Lanzar una nueva rutina para insertar los datos de manera no bloqueante
     */
    fun insert(transaccion: Transaccion) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(transaccion)
    }
}