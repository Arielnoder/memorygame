package com.example.memorygame

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

@Composable
fun LeaderBoard(navController: NavController) {

    // create a noraml array to store the names and scores
    val names = arrayOfNulls<String>(1)
    val scores = mutableListOf<Int>()







    Box(modifier = androidx.compose.ui.Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
    Text(
        // display the names and scores in a list
        text = getLeaderBoard()




    )
}






    }

fun getLeaderBoard(): String {
    val db = Firebase.firestore
    var leaderBoard = ""
    db.collection("users")
        .get()
        .addOnSuccessListener { result ->
            for (document in result) {
                leaderBoard += document.data["name"].toString() + " " + document.data["score"].toString() + "\n"
                Log.d("TAG", "${document.id} => ${document.data}")
            }
        }
        .addOnFailureListener { exception ->
            Log.d("TAG", "Error getting documents: ", exception)
        }
    return leaderBoard
}




