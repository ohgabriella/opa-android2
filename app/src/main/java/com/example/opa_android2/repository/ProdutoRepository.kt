package com.example.opa_android2.repository

import androidx.lifecycle.LiveData
import com.example.opa_android2.database.dao.ProdutoDAO
import com.example.opa_android2.model.Produto

class ProdutoRepository(private val produtoDAO: ProdutoDAO) {

    val all: LiveData<List<Produto>> = produtoDAO.all()

    suspend fun add(produto: Produto) {
        produtoDAO.add(produto)
    }
}