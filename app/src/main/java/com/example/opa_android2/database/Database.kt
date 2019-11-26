package com.example.opa_android2.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.opa_android2.database.dao.ProdutoDAO
import com.example.opa_android2.model.Produto

@Database(entities = arrayOf(Produto::class), version = 1, exportSchema = false)
abstract class Database : RoomDatabase() {

    abstract fun produtoDao(): ProdutoDAO

}