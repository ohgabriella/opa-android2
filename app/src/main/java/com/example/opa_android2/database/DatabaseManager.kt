package com.example.opa_android2.database

import androidx.room.Room
import com.example.opa_android2.ProdutoApplication
import com.example.opa_android2.database.dao.ProdutoDAO

object DatabaseManager {

    private var dbInstance: Database

    init {
        val appContext = ProdutoApplication.getInstance().applicationContext
        dbInstance = Room.databaseBuilder(
            appContext,
            Database::class.java,
            "produtodb.sqlite"
        ).build()
    }

    fun produtoDAO(): ProdutoDAO {
        return dbInstance.produtoDao()
    }


}