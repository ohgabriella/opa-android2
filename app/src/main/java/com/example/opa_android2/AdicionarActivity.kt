package com.example.opa_android2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
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

        inputNome = findViewById(R.id.inputNome)
        inputPreco = findViewById(R.id.inputPreco)
        inputQtd = findViewById(R.id.inputQtd)
        inputDesc = findViewById(R.id.inputDesc)
        cadastrarButton = findViewById(R.id.cadastrarButton)

        cadastrarButton.setOnClickListener {
            val replyIntent = Intent()
            if (inputNome.text.isNotEmpty() && inputPreco.text.isNotEmpty() && inputQtd.text.isNotEmpty() && inputDesc.text.isNotEmpty()) {
                var nome = inputNome.text.toString()
                var preco = inputPreco.text.toString()
                var qtd = inputQtd.text.toString()
                var desc = inputDesc.text.toString()

                var produto = Produto(0, nome, preco, qtd, desc)

//                replyIntent.putExtra(EXTRA_REPLY, produto)

                replyIntent.putExtra("produto", produto)

                setResult(Activity.RESULT_OK, replyIntent)

                finish()
            } else {
                Toast.makeText(AdicionarActivity@ this, "Preencha os campos", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
    }
}