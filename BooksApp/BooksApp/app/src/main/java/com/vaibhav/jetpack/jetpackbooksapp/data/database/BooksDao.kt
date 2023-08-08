package com.vaibhav.jetpack.jetpackbooksapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

// TODO: 2: Use Kotlin.Flow for the Data continuation from Database/Repo
@Dao
interface BooksDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertBook(book: Book)

    @Query("SELECT * FROM books_table WHERE id = :id")
    fun getBook(id: Int): Book

    @Query("SELECT * FROM books_table")
    fun getAllBooks(): List<Book>
}