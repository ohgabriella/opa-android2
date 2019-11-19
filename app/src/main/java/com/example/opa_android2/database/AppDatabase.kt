package com.example.opa_android2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.opa_android2.database.dao.ProdutoDAO
import com.example.opa_android2.database.dao.UsersDAO
import com.example.opa_android2.model.Produto
import com.example.opa_android2.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Produto::class, User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun produtoDao(): ProdutoDAO
    abstract fun usersDao(): UsersDAO

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "produto_database"
                )
                    .addCallback(ProdutoDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }

        private class ProdutoDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            /**
             * Override the onOpen method to populate the database.
             * For this sample, we clear the database every time it is created or opened.
             */
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.produtoDao())
                    }
                }
            }
        }

        /**
         * Populate the database in a new coroutine.
         * If you want to start with more words, just add them.
         */
        suspend fun populateDatabase(produtoDAO: ProdutoDAO) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            produtoDAO.deleteAll()

            var produto = Produto(0, "pao", "5", "5", "pao gostosao")
            produtoDAO.add(produto)
        }
    }


}