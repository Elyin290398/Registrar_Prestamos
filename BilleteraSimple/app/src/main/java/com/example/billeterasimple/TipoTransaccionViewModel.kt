package com.example.billeterasimple

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TipoTransaccionViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: RepositorioTipoTransaccion

    val allTransacciones: LiveData<List<TipoTransaccion>>

    init {
        val tipoTransaccionesDao = TransaccionRoomDatabase.getDatabase(application, viewModelScope).tipoTransaccionDao()
        repository = RepositorioTipoTransaccion(tipoTransaccionesDao)
        allTransacciones = repository.allTipoTransacciones
    }

    /**
     * Lanzar una nueva rutina para insertar los datos de manera no bloqueante
     */
    fun insert(trantipoTransacionsaccion: TipoTransaccion) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(trantipoTransacionsaccion)
    }

}