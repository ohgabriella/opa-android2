package com.example.opa_android2.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.opa_android2.model.Produto

@Dao
interface ProdutoDAO {

    @Query("SELECT * FROM produto ORDER BY id ASC")
    fun findAll() : List<Produto>

    @Query("SELECT * FROM produto")
    fun getById(id:Long): Produto?

    @Insert
    fun insert(produto: Produto)

    @Update
    fun update(produto: Produto)

    @Delete
    fun delete(produto : Produto)
}