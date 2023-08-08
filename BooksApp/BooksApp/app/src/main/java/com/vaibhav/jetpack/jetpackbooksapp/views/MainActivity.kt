package com.vaibhav.jetpack.jetpackbooksapp.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.vaibhav.jetpack.jetpackbooksapp.data.viewmodel.BooksViewModel
import com.vaibhav.jetpack.jetpackbooksapp.navigation.Screen
import com.vaibhav.jetpack.jetpackbooksapp.ui.theme.JetpackBooksAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackBooksAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    val booksViewModel = BooksViewModel(application)
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.HomeScreen.route
                    ) {
                        composable(route = Screen.HomeScreen.route) {
                            HomeScreen(navController = navController)
                        }
                        composable(route = Screen.AddBookScreen.route) {
                            AddBooksScreen(booksViewModel = booksViewModel)
                        }
                        composable(route = Screen.AllBooksScreen.route) {
                            AllBooksScreen(
                                navController = navController,
                                booksViewModel = booksViewModel
                            )
                        }
                        composable(
                            route = Screen.BookDetailsScreen.route + "/{book_id}",
                            arguments = listOf(
                                navArgument("book_id") {
                                    type = NavType.IntType
                                    defaultValue = -1
                                    nullable = false
                                }
                            )
                        ) {
                            val id = it.arguments?.getInt("book_id") ?: -1
                            BookDetails(id, booksViewModel = booksViewModel)
                        }
                    }
                }
            }
        }
    }
}