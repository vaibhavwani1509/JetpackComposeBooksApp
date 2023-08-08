package com.vaibhav.jetpack.jetpackbooksapp.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.vaibhav.jetpack.jetpackbooksapp.R
import com.vaibhav.jetpack.jetpackbooksapp.data.database.Book
import com.vaibhav.jetpack.jetpackbooksapp.data.viewmodel.BooksViewModel
import com.vaibhav.jetpack.jetpackbooksapp.navigation.Screen
import com.vaibhav.jetpack.jetpackbooksapp.views.utils.TitleText

@Composable
fun AllBooksScreen(navController: NavController, booksViewModel: BooksViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        booksViewModel.getBooks()
        val books: List<Book> by booksViewModel.allBooks.observeAsState(initial = listOf())
        TitleText(text = stringResource(id = R.string.all_books_title), modifier = Modifier)
        LazyColumn(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(15.dp)) {
            items(books) { book ->
                OutlinedCard(
                    Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .clickable {
                            // Navigate to book details
                            navController.navigate(route = Screen.BookDetailsScreen.route + "/" + book.id)
                        },
                ) {
                    ConstraintLayout(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(10.dp)
                    ) {
                        val (book_icon, name, author, category) = createRefs()
                        Image(
                            painter = painterResource(id = R.drawable.book),
                            null,
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier
                                .wrapContentSize()
                                .constrainAs(book_icon) {
                                    top.linkTo(parent.top)
                                    absoluteLeft.linkTo(parent.absoluteLeft)
                                }
                        )
                        Text(
                            text = book.name,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp, 0.dp, 0.dp, 5.dp)
                                .constrainAs(name) {
                                    top.linkTo(parent.top)
                                    absoluteLeft.linkTo(book_icon.absoluteRight)
                                    bottom.linkTo(author.top)
                                },
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        Text(
                            text = book.author,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp, 0.dp, 0.dp, 5.dp)
                                .constrainAs(author) {
                                    top.linkTo(name.bottom)
                                    absoluteLeft.linkTo(book_icon.absoluteRight)
                                },
                            fontStyle = FontStyle.Italic
                        )
                        Text(
                            text = "(${book.category})",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp, 0.dp, 0.dp, 0.dp)
                                .constrainAs(category) {
                                    top.linkTo(author.bottom)
                                    absoluteLeft.linkTo(book_icon.absoluteRight)
                                },
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }
    }
}
