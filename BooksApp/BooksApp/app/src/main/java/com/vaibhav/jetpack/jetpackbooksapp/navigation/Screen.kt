package com.vaibhav.jetpack.jetpackbooksapp.navigation

sealed class Screen(val route: String) {
    object HomeScreen : Screen("main_screen")
    object AddBookScreen : Screen("add_a_book")
    object BookDetailsScreen : Screen("book_details")
    object AllBooksScreen : Screen("all_books")
}
