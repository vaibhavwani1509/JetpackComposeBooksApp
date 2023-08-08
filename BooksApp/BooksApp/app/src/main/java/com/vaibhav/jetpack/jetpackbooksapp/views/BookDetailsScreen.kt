package com.vaibhav.jetpack.jetpackbooksapp.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.vaibhav.jetpack.jetpackbooksapp.data.viewmodel.BooksViewModel

@Composable
fun BookDetails(id: Int, booksViewModel: BooksViewModel) {
    booksViewModel.getBook(id)
    val book = booksViewModel.getBook.observeAsState().value
    book ?: return
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(rememberScrollState())
    ) {
        AsyncImage(
            model = "https://picsum.photos/id/24/500",
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp),
            contentScale = ContentScale.FillBounds
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = book.name,
            fontFamily = FontFamily.Monospace,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "by " + book.author,
            fontSize = 15.sp,
            fontStyle = FontStyle.Italic
        )
        Text(
            text = "(${book.category})"
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = book.description
        )
    }
}