package com.example.opa_android2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var user: EditText
    lateinit var password: EditText
    lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        user = findViewById(R.id.user)
        password = findViewById(R.id.password)
        loginButton = findViewById(R.id.loginButton)

        loginButton.setOnClickListener{
            if(user.text.toString().isNotEmpty() && password.text.toString().isNotEmpty()){
                val intent = Intent(MainActivity@this, ListActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(MainActivity@this, "Não é possível entrar com login e senha vazios", Toast.LENGTH_LONG).show()
            }
        }
    }
}