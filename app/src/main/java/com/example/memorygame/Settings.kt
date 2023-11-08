package com.example.memorygame

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.memorygame.model.ImageRepository

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Settings(navController: NavController, repository: ImageRepository) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Settings") },

                )
        },
        content = {
            Column(
                modifier = Modifier.padding(120.dp)
                ,
                verticalArrangement = Arrangement.Center,


                ) {
                Button(onClick = { navController.navigate("Categories") }) {
                    Text(text = "Categories")
                }
                Spacer(modifier = Modifier.height(50.dp))
                Button(onClick = { navController.navigate("Background") }) {

                    Text(text = "Background")
                }
            }
        }
    )

}

