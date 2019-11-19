package com.example.opa_android2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.room.Room
import com.example.opa_android2.database.AppDatabase
import com.example.opa_android2.model.Produto

class AdicionarActivity : AppCompatActivity() {

    lateinit var inputNome: EditText
    lateinit var inputPreco: EditText
    lateinit var inputQtd: EditText
    lateinit var inputDesc: EditText
    lateinit var cadastrarButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adicionar)

        val database =
            Room.databaseBuilder(this, AppDatabase::class.java, "produto-database").build()

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

                var produto = Produto(0, nome, preco, qtd, desc)

                database.produtoDao().add(produto)

                var i = Intent(AdicionarActivity@ this, ListActivity::class.java)
                startActivity(i)

                finish()
            } else {
                Toast.makeText(AdicionarActivity@ this, "Preencha os campos", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }
}
