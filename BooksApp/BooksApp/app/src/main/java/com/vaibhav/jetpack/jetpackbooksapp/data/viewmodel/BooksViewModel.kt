package com.vaibhav.jetpack.jetpackbooksapp.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.vaibhav.jetpack.jetpackbooksapp.data.database.Book
import com.vaibhav.jetpack.jetpackbooksapp.data.database.BooksDao
import com.vaibhav.jetpack.jetpackbooksapp.data.database.BooksDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BooksViewModel(application: Application) : AndroidViewModel(application) {

    private val dao: BooksDao
    private val _allBooks = MutableLiveData<List<Book>>()
    private val _getBook = MutableLiveData<Book>()

    val allBooks: LiveData<List<Book>> = _allBooks
    val getBook: LiveData<Book> = _getBook

    init {
        dao = BooksDatabase.getInstance(application).booksDao()
    }

    fun getBooks() {
        viewModelScope.launch(Dispatchers.IO) {
            _allBooks.postValue(dao.getAllBooks())
        }
    }

    fun getBook(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _getBook.postValue(dao.getBook(id))
        }
    }

    fun addBook(book: Book) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.insertBook(book)
        }
        getBooks()
    }
}