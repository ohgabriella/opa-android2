package com.example.opa_android2

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

class EditarActivity : AppCompatActivity() {

    lateinit var editarNome: EditText
    lateinit var editarPreco: EditText
    lateinit var editarQtd: EditText
    lateinit var editarDesc: EditText
    lateinit var confButton: Button
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar)

        editarNome = findViewById(R.id.editarNome)
        editarPreco = findViewById(R.id.editarPreco)
        editarQtd = findViewById(R.id.editarQtd)
        editarDesc = findViewById(R.id.editarDesc)

        confButton = findViewById(R.id.confButton)

        var produto = intent.getParcelableExtra<Produto>("produto")

        if(produto != null) {
            editarNome.setText(produto.nome)
            editarPreco.setText(produto.preco)
            editarQtd.setText(produto.quantidade)
            editarDesc.setText(produto.descricao)
        }

        confButton.setOnClickListener{
            produto.nome = editarNome.text.toString()
            produto.preco = editarPreco.text.toString()
            produto.quantidade = editarQtd.text.toString()
            produto.descricao = editarDesc.text.toString()

            atualizarProduto(produto)

            Toast.makeText(EditarActivity@this, "Produto atualizado com sucesso!", Toast.LENGTH_LONG).show()
        }
    }

    fun atualizarProduto(produto: Produto) {
        doAsync {
            var produto = ProdutoService.atualizarProduto(produto)

            uiThread {
                Log.e("EditarActivity", produto.toString())
            }
        }
    }
}
