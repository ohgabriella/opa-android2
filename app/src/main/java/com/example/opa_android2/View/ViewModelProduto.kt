package com.example.opa_android2.View

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.opa_android2.database.AppDatabase
import com.example.opa_android2.model.Produto
import com.example.opa_android2.repository.ProdutoRepository
import kotlinx.coroutines.launch

class ViewModelProduto (application: Application) : AndroidViewModel(application) {
    private val repository: ProdutoRepository
    // LiveData gives us updated words when they change.
    val allProdutos: LiveData<List<Produto>>

    init {
        // Gets reference to WordDao from WordRoomDatabase to construct
        // the correct WordRepository.
        val produtoDao = AppDatabase.getDatabase(application, viewModelScope).produtoDao()
        repository = ProdutoRepository(produtoDao)
        allProdutos = repository.all
    }

    /**
     * The implementation of insert() in the database is completely hidden from the UI.
     * Room ensures that you're not doing any long running operations on
     * the main thread, blocking the UI, so we don't need to handle changing Dispatchers.
     * ViewModels have a coroutine scope based on their lifecycle called
     * viewModelScope which we can use here.
     */
    suspend fun add(produto: Produto) = viewModelScope.launch {
        repository.add(produto)
    }
}