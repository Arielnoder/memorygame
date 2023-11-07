package com.example.memorygame

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Home(navController: NavController) {
    // 2 buttons - 1 to start a new game, 1 to view high scores
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Memory Game") },

            )
        },
        content = {
            Column(
                modifier = Modifier.padding(120.dp)
                    ,
                verticalArrangement = Arrangement.Center,


            ) {
                Button(onClick = { navController.navigate("Game") }) {
                    Text(text = "New Game")
                }
                Spacer(modifier = Modifier.height(50.dp))
                Button(onClick = { /*TODO*/ }) {

                    Text(text = "High Scores")
                }
            }
        }
    )

}