package com.example.opa_android2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.opa_android2.database.ProdutoService
import com.example.opa_android2.model.Produto
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class AdicionarActivity : AppCompatActivity() {

    lateinit var inputNome: EditText
    lateinit var inputPreco: EditText
    lateinit var inputQtd: EditText
    lateinit var inputDesc: EditText
    lateinit var cadastrarButton: Button
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adicionar)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar!!.setDisplayShowTitleEnabled(false)

        inputNome = findViewById(R.id.inputNome)
        inputPreco = findViewById(R.id.inputPreco)
        inputQtd = findViewById(R.id.inputQtd)
        inputDesc = findViewById(R.id.inputDesc)
        cadastrarButton = findViewById(R.id.cadastrarButton)

        cadastrarButton.setOnClickListener {
            if (inputNome.text.isNotEmpty() && inputPreco.text.isNotEmpty() && inputQtd.text.isNotEmpty() && inputDesc.text.isNotEmpty()) {
                var nome = inputNome.text.toString()
                var preco = inputPreco.text.toString()
                var qtd = inputQtd.text.toString()
                var desc = inputDesc.text.toString()

                var produto = Produto()
                produto.nome = nome
                produto.preco = preco
                produto.quantidade = qtd
                produto.descricao = desc

                inserir(produto)

                val intent = Intent(AdicionarActivity@ this, ListActivity::class.java)
                startActivity(intent)

            } else {
                Toast.makeText(AdicionarActivity@ this, "Preencha os campos", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    fun inserir(produto: Produto) {
        doAsync {
            ProdutoService.inserirProduto(produto)
            uiThread {
                Log.e("ListActivity", "Produto inserido com Sucesso!")
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}

