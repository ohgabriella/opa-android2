package com.example.opa_android2

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

class EditarActivity : AppCompatActivity() {

    lateinit var editarNome: EditText
    lateinit var editarPreco: EditText
    lateinit var editarQtd: EditText
    lateinit var editarDesc: EditText
    lateinit var confButton: Button
    lateinit var toolbar: Toolbar


    companion object{
        const val RESULT_CODE_ATUALIZADO = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar!!.setDisplayShowTitleEnabled(false)

        editarNome = findViewById(R.id.editarNome)
        editarPreco = findViewById(R.id.editarPreco)
        editarQtd = findViewById(R.id.editarQtd)
        editarDesc = findViewById(R.id.editarDesc)

        confButton = findViewById(R.id.confButton)

        var produto = intent.getParcelableExtra<Produto>("produto")

        if (produto != null) {
            editarNome.setText(produto.nome)
            editarPreco.setText(produto.preco)
            editarQtd.setText(produto.quantidade)
            editarDesc.setText(produto.descricao)
        }

        confButton.setOnClickListener {
            produto.nome = editarNome.text.toString()
            produto.preco = editarPreco.text.toString()
            produto.quantidade = editarQtd.text.toString()
            produto.descricao = editarDesc.text.toString()
            var i = Intent()
            i.putExtra("produto", produto)
            setResult(EditarActivity.RESULT_CODE_ATUALIZADO, i)
            finish()

            atualizarProduto(produto)

            Toast.makeText(
                EditarActivity@ this,
                "Produto atualizado com sucesso!",
                Toast.LENGTH_LONG
            ).show()
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

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}
