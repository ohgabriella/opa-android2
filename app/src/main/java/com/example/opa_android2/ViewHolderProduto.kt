package com.example.opa_android2

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.opa_android2.model.Produto

class ViewHolderProduto (var item : View) : RecyclerView.ViewHolder(item) {
    fun bindView( produto: Produto ){
        var textNome = item.findViewById<TextView>(R.id.textNome)
        var textPreco = item.findViewById<TextView>(R.id.textPreco)
        var textQtd = item.findViewById<TextView>(R.id.textQtd)
        var textDesc = item.findViewById<TextView>(R.id.textDescricao)

        textNome.text = produto.nome
        textPreco.text = produto.preco
        textQtd.text = produto.quantidade
        textDesc.text = produto.descricao

    }
}