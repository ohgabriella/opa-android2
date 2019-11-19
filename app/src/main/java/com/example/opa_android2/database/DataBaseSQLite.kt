package com.example.opa_android2.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper


class DataBaseSQLite(var context: Context, var dbName: String, var version: Int) :
    SQLiteOpenHelper(context, dbName, null, version) {
    override fun onCreate(db: SQLiteDatabase?) {
        var sql = "CREATE TABLE produto (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT," +
                "preco TEXT," +
                "quantidade TEXT," +
                "descricao TEXT)"
        try{
            db?.execSQL(sql);
        }catch (e:SQLiteException){
            //Log.e(TAG,"Error ao tentar criar o banco: ${e.message}")
        }

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        var sql = "DROP TABLE IF EXISTS produto"
        try{
            db?.execSQL(sql)
            onCreate(db)
        }catch (e:SQLiteException){
            //Log.e(TAG, "Error ao tentar atualizar o banco: ${e.message}");
        }

    }
}