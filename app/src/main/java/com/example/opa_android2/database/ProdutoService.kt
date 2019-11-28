package com.example.opa_android2.database

import com.example.opa_android2.database.Database
import com.example.opa_android2.model.Produto

object ProdutoService {

    fun getProdutos(): List<Produto> = DatabaseManager.produtoDAO().findAll()
    fun getProduto(id:Long):Produto? = DatabaseManager.produtoDAO().getById(id)
    fun inserirProduto(produto: Produto) = DatabaseManager.produtoDAO().insert(produto)
    fun atualizarProduto(produto: Produto) = DatabaseManager.produtoDAO().update(produto)
    fun deletarPessoa(produto: Produto) = DatabaseManager.produtoDAO().delete(produto)
}