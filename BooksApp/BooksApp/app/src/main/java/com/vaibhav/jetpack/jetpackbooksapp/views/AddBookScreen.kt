package com.vaibhav.jetpack.jetpackbooksapp.views

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.vaibhav.jetpack.jetpackbooksapp.R
import com.vaibhav.jetpack.jetpackbooksapp.data.database.Book
import com.vaibhav.jetpack.jetpackbooksapp.data.viewmodel.BooksViewModel
import com.vaibhav.jetpack.jetpackbooksapp.views.utils.CustomButton
import com.vaibhav.jetpack.jetpackbooksapp.views.utils.TitleText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddBooksScreen(booksViewModel: BooksViewModel) {
    val context = LocalContext.current
    var bookName by rememberSaveable { mutableStateOf("") }
    var author by rememberSaveable { mutableStateOf("") }
    var category by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TitleText(
                text = stringResource(id = R.string.add_book_title),
                modifier = Modifier
            )
            OutlinedTextField(
                value = bookName,
                label = { Text(stringResource(id = R.string.book_name)) },
                onValueChange = {
                    bookName = it
                })
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = author,
                label = { Text(stringResource(id = R.string.author)) },
                onValueChange = {
                    author = it
                })
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = category,
                label = { Text(stringResource(id = R.string.category)) },
                onValueChange = {
                    category = it
                })
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = description,
                label = { Text(stringResource(id = R.string.description)) },
                onValueChange = {
                    description = it
                })
            Spacer(modifier = Modifier.height(30.dp))
            CustomButton(stringResource(id = R.string.add_book)) {
                // Create the book object
                val book = Book(bookName, author, category, description)
                // Add the book to the database
                booksViewModel.addBook(book)
                // Clear text fields
                bookName = ""
                author = ""
                category = ""
                description = ""

                // Show success/error message
                // TODO: 3. Add sanity checks and error prompts for database operations
                Toast.makeText(context, "Book added successfully", Toast.LENGTH_SHORT).show()
            }
        }
    }
}