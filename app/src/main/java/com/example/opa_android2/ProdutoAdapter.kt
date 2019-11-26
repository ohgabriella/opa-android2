package com.example.opa_android2

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.opa_android2.model.Produto

class ProdutoAdapter (var listProduto : ArrayList<Produto>) : RecyclerView.Adapter<ViewHolderProduto>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderProduto {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_item_produto, parent, false)
        return ViewHolderProduto(itemView)
    }

    override fun getItemCount(): Int {
        return listProduto.size
    }

    override fun onBindViewHolder(holder: ViewHolderProduto, position: Int) {
        var produto = listProduto[position]
        holder.bindView(produto)
    }

}