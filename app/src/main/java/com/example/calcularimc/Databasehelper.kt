package com.example.calcularimc

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "imc_app.db"
        private const val DATABASE_VERSION = 1

        private const val TABLE_IMC_CONSULTAS = "imc_consultas"
        private const val COLUMN_ID = "id"
        private const val COLUMN_USUARIO_ID = "usuario_id"
        private const val COLUMN_PESO = "peso"
        private const val COLUMN_ALTURA = "altura"
        private const val COLUMN_IMC = "imc"
        private const val COLUMN_DATA_CONSULTA = "data_consulta"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableIMCConsultas = ("CREATE TABLE $TABLE_IMC_CONSULTAS ("
                + "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "$COLUMN_USUARIO_ID INTEGER NOT NULL, "
                + "$COLUMN_PESO REAL NOT NULL, "
                + "$COLUMN_ALTURA REAL NOT NULL, "
                + "$COLUMN_IMC REAL NOT NULL, "
                + "$COLUMN_DATA_CONSULTA TIMESTAMP DEFAULT CURRENT_TIMESTAMP)"
                )
        db.execSQL(createTableIMCConsultas)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_IMC_CONSULTAS")
        onCreate(db)
    }
}
