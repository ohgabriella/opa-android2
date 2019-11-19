package com.example.opa_android2.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "produto")
class Produto (
    @PrimaryKey(autoGenerate = true)
    val id : Long = 0,
    val nome : String,
    val preco: String,
    val quantidade: String,
    val descricao : String ) {
}