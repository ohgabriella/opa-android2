package com.example.opa_android2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.opa_android2.database.ProdutoService
import com.example.opa_android2.model.Produto
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class ListActivity : AppCompatActivity() {

    lateinit var recycleListProduto: RecyclerView
    lateinit var addButton: FloatingActionButton
    lateinit var listProdutos: List<Produto>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        recycleListProduto = findViewById(R.id.recycleListProduto)
        addButton = findViewById(R.id.addButton)

        listProdutos = ArrayList<Produto>()

        val adapter = ProdutoAdapter(listProdutos, { partItem: Produto  -> partItemClicked(partItem) })
        recycleListProduto.adapter = adapter
        recycleListProduto.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        addButton.setOnClickListener {
            var i = Intent(ListActivity@ this, AdicionarActivity::class.java)
            startActivity(i)
        }

        listarTodos()
    }

    private fun partItemClicked(produtoItem : Produto) {
        Toast.makeText(this, "Clicked: ${produtoItem.nome}", Toast.LENGTH_LONG).show()

        // Chama a outra activity, passa o ID como uma string parameter
        val showDetailActivityIntent = Intent(this, ExibirActivity::class.java)
        showDetailActivityIntent.putExtra("produto", produtoItem)
        startActivity(showDetailActivityIntent)
    }

    fun listarTodos() {
        doAsync {
            listProdutos = ProdutoService.getProdutos()

            uiThread {
                Log.e("ListActivity", listProdutos.toString())
                recycleListProduto.adapter = ProdutoAdapter(listProdutos, { partItem: Produto  -> partItemClicked(partItem) })
                recycleListProduto.adapter!!.notifyDataSetChanged()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        listarTodos()
    }
}