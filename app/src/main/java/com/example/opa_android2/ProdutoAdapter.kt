package com.example.opa_android2

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.opa_android2.model.Produto

class ProdutoAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<ViewHolderProduto>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var produtos = emptyList<Produto>() // Cached copy of words

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderProduto {
        var itemView = inflater.inflate(R.layout.layout_item_produto, parent, false)
        return ViewHolderProduto(itemView)
    }

    override fun getItemCount(): Int {
        return produtos.size
    }

    override fun onBindViewHolder(holder: ViewHolderProduto, position: Int) {
        var produto = produtos[position]
        holder.bindView(produto)
    }

    internal fun setProduto(words: List<Produto>) {
        this.produtos = produtos
        notifyDataSetChanged()
    }

}