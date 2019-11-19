package com.example.opa_android2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.room.Database
import androidx.room.Room
import com.example.opa_android2.database.AppDatabase
import com.example.opa_android2.database.dao.UsersDAO
import com.example.opa_android2.model.User

class MainActivity : AppCompatActivity() {

    lateinit var user: EditText
    lateinit var password: EditText
    lateinit var loginButton: Button

    private lateinit var userDao: UsersDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = Room.databaseBuilder(this, AppDatabase::class.java, "produto-database").build()

        user = findViewById(R.id.user)
        password = findViewById(R.id.password)
        loginButton = findViewById(R.id.loginButton)

//        var users = User(1, user.toString(), password.toString())
//        database.usersDao().add(users)

        loginButton.setOnClickListener{
            if(user.text.toString().isNotEmpty() && password.text.toString().isNotEmpty()){
                val intent = Intent(MainActivity@this, ListActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(ExibirActivity@this, "Não é possível entrar com login e senha vazios", Toast.LENGTH_LONG).show()
            }
        }
    }
}
