package com.example.memorygame

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.room.Query
import com.example.memorygame.Controller.ImageController
import com.example.memorygame.model.ImageRepository
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeaderBoard(navController: NavController, repository : ImageRepository) {



    var leaderboardData by remember { mutableStateOf("") }

    // Call the getLeaderBoard function and update the leaderboardData when data is available
    ImageController(repository).getLeaderBoard { data ->
        leaderboardData = data
    }
    Scaffold {
        TopAppBar( {
            Text(text = "Leaderboard",
                fontSize = 30.sp,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                fontFamily = androidx.compose.ui.text.font.FontFamily.Serif,
                textDecoration = androidx.compose.ui.text.style.TextDecoration.Underline




                )

        }

        )
    }
    // create a table to display the leaderboard data

    LazyColumn {
        item {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(70.dp)
            ) {
                Text(
                    text = leaderboardData,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .border(1.dp, Color.Black)
                        .padding(2.dp)
                )
            }
        }
    }









        }















