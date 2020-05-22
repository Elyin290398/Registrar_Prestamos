package com.example.billeterasimple

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


// Anota la clase como una base de datos de sala con una tabla (entidad) de la clase Word
@Database(entities = arrayOf(Transaccion::class,TipoTransaccion::class), version = 1, exportSchema = false)
abstract class TransaccionRoomDatabase: RoomDatabase() {

    abstract fun transaccionDao(): TransaccionDao
    abstract fun tipoTransaccionDao(): TipoTransaccionDao

    private class transaccionDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                   // var wordDao = database.transaccionDao()

                   // Delete all content here.
                  // wordDao.deleteAll()
/*
                    // Add sample words.
                    var word = Word("Hello")
                    wordDao.insert(word)
                    word = Word("World!")
                    wordDao.insert(word)

                    // TODO: Add your own words!
                    word = Word("Training!")
                    wordDao.insert(word)*/
                }
            }
        }
    }
    companion object {
        @Volatile
        private var INSTANCE: TransaccionRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): TransaccionRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TransaccionRoomDatabase::class.java,
                    "prestamito_bd"
                )
                    .addCallback(transaccionDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}