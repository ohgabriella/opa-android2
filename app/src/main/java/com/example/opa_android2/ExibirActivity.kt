package com.example.opa_android2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.opa_android2.database.ProdutoService
import com.example.opa_android2.model.Produto
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class ExibirActivity : AppCompatActivity() {

    var nome: String? = null
    var preco: String? = null
    var estoque: String? = null
    var descricao: String? = null

    lateinit var produto: Produto
    var indice: Long = 0

    lateinit var textNome: TextView
    lateinit var textPreco: TextView
    lateinit var textEstoque: TextView
    lateinit var textDescricao: TextView
    lateinit var edtButton: Button
    lateinit var excluirButton: Button
    lateinit var toolbar: Toolbar

    companion object {
        const val REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exibir)

        toolbar = findViewById(R.id.toolbar)

        setSupportActionBar(toolbar)

        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)

        textNome = findViewById(R.id.textNome)
        textPreco = findViewById(R.id.textPreco)
        textEstoque = findViewById(R.id.textEstoque)
        textDescricao = findViewById(R.id.textDescricao)
        edtButton = findViewById(R.id.edtButton)
        excluirButton = findViewById(R.id.excluirButton)

        produto = intent.getParcelableExtra<Produto>("produto")
        indice = intent.getLongExtra("position", 0)

        if (produto != null) {
            nome = produto.nome
            preco = produto.preco
            estoque = produto.quantidade
            descricao = produto.descricao

            textNome.text = "Nome: ${nome}"
            textPreco.text = "Preço: ${preco}"
            textEstoque.text = "Quantidade em estoque: ${estoque}"
            textDescricao.text = "Descrição: ${descricao}"
        }

        edtButton.setOnClickListener {
            var i = Intent(ExibirActivity@ this, EditarActivity::class.java)
            i.putExtra("produto", produto)
            startActivityForResult(i, REQUEST_CODE)
        }

        excluirButton.setOnClickListener {
            excluirProduto(produto)
            var i = Intent(ExibirActivity@ this, ListActivity::class.java)
            startActivity(i)

            Toast.makeText(ExibirActivity@ this, "Produto excluido com sucesso!", Toast.LENGTH_LONG)
                .show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ExibirActivity.REQUEST_CODE && resultCode == EditarActivity.RESULT_CODE_ATUALIZADO) {
            var produto = data?.getParcelableExtra<Produto>("produto")
            if (produto != null) {
                this.produto = produto

                textNome.text = "Nome: ${produto.nome}"
                textPreco.text = "Preço: ${produto.preco}"
                textEstoque.text = "Quantidade em estoque: ${produto.quantidade}"
                textDescricao.text = "Descrição: ${produto.descricao}"

            }
        }
    }

    fun excluirProduto(produto: Produto) {
        doAsync {
            var produto = ProdutoService.deletarProduto(produto)

            uiThread {
                Log.e("ExibirActivity", produto.toString())
            }
        }
    }

    fun getProdutoPorId(id: Long) {
        doAsync {
            var produto = ProdutoService.getProduto(id)

            uiThread {
                Log.e("ExibirActivity", produto.toString())
            }
        }
    }

    override fun onStart() {
        super.onStart()
        getProdutoPorId(indice)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}
