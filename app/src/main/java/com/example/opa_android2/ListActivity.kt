package com.example.opa_android2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.opa_android2.database.ProdutoService
import com.example.opa_android2.model.Produto
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class ListActivity : AppCompatActivity() {

    private val newActivityRequestCode = 1

    lateinit var recycleListProduto: RecyclerView
    lateinit var addButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        recycleListProduto = findViewById(R.id.recycleListProduto)
        addButton = findViewById(R.id.addButton)

        //listo os meus produtos
        listarTodos()

        val adapter = ProdutoAdapter(this)
        recycleListProduto.adapter = adapter
        recycleListProduto.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        addButton.setOnClickListener {
            var i = Intent(ListActivity@ this, AdicionarActivity::class.java)
            startActivityForResult(i, newActivityRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.getStringExtra(AdicionarActivity.EXTRA_REPLY)?.let {
                var produto = data?.getParcelableExtra<Produto>("produto")
                if (produto != null) {
                    inserir(produto)
//                    arrayAdapterProduto.notifyDataSetChanged()
                } else {
                    Log.e("MainActivity", "Erro ao retornar uma produto")
                }

            }
        }
    }

    fun listarTodos() {
        doAsync {
            var produtos = ProdutoService.getProdutos()

            uiThread {
                Log.e("ListActivity", produtos.toString())
            }
        }
    }

    fun inserir(produto: Produto){
        doAsync {
            ProdutoService.inserirProduto(produto)
            uiThread {
                Log.e("ListActivity", "Produto inserido com Sucesso!")
            }
        }
    }
}