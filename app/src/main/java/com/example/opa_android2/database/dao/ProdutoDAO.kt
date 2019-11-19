package com.example.opa_android2.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.opa_android2.model.Produto

@Dao
interface ProdutoDAO {

    @Query("SELECT * FROM produto ORDER BY id ASC")
    fun all() : LiveData<List<Produto>>

    @Insert
    fun add(vararg produto: Produto)

//    @Query("DELETE FROM produto WHERE id = :id")
//    fun deleteProduto(Produto produto)

    @Query("DELETE FROM produto")
    suspend fun deleteAll()



}