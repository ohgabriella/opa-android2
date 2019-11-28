package com.example.opa_android2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.example.opa_android2.model.Produto

class ExibirActivity : AppCompatActivity() {

    var nome: String? = null
    var preco: String? = null
    var estoque: String? = null
    var descricao: String? = null

    lateinit var produto: Produto
    var indice: Int = 0

    lateinit var textNome: TextView
    lateinit var textPreco: TextView
    lateinit var textEstoque: TextView
    lateinit var textDescricao: TextView
    lateinit var edtButton: Button
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exibir)

        textNome = findViewById(R.id.textNome)
        textPreco = findViewById(R.id.textPreco)
        textEstoque = findViewById(R.id.textEstoque)
        textDescricao = findViewById(R.id.textDescricao)
        edtButton = findViewById(R.id.edtButton)

        produto = intent.getParcelableExtra<Produto>("produto")
        indice = intent.getIntExtra("position", 0)

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
            startActivity(i)
        }
    }
}
