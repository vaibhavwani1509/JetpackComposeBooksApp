package com.vaibhav.jetpack.jetpackbooksapp.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.vaibhav.jetpack.jetpackbooksapp.navigation.Screen
import com.vaibhav.jetpack.jetpackbooksapp.views.utils.CustomButton

/**
 * TODO in the app
 * 1. Implement scaffold for the UI
 * 2. Use Kotlin.Flow for the Data continuation from Database/Repo
 * 3. Add sanity checks and error prompts for database operations
 */
@Composable
fun HomeScreen(navController: NavController) {
    // TODO: 1 : Implement a scaffold
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Books App",
                fontFamily = FontFamily.Monospace,
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(100.dp))
            CustomButton("List all books") { navController.navigate(route = Screen.AllBooksScreen.route) }
            Spacer(modifier = Modifier.height(50.dp))
            CustomButton("Add a book") { navController.navigate(Screen.AddBookScreen.route) }
        }
    }
}