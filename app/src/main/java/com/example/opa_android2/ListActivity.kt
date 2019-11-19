package com.example.opa_android2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.opa_android2.View.ViewModelProduto
import com.example.opa_android2.database.AppDatabase
import com.example.opa_android2.model.Produto
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListActivity : AppCompatActivity() {

    private val newActivityRequestCode = 1
    private lateinit var produtoViewModel: ViewModelProduto

    lateinit var recycleListProduto: RecyclerView
    lateinit var addButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        recycleListProduto = findViewById(R.id.recycleListProduto)
        addButton = findViewById(R.id.addButton)


        recycleListProduto.adapter = ProdutoAdapter(this)
        recycleListProduto.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        addButton.setOnClickListener {
            var i = Intent(ListActivity@this, AdicionarActivity::class.java)
            startActivityForResult(i, newActivityRequestCode)
        }

    }
}
