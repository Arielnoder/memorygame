package com.example.memorygame.Controller

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.memorygame.model.Image
import com.example.memorygame.model.ImageRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.launch

class ImageController(private val repository: ImageRepository) : ViewModel() {

    val allImages: LiveData<List<Image>> = repository.getImages().asLiveData()
    val db = Firebase.firestore
    val user = FirebaseAuth.getInstance().currentUser


    fun insert(image: Image) = viewModelScope.launch {
        repository.insert(image)
    }

    fun getImages(): LiveData<List<Image>> {
        return allImages
    }

    fun getImageName(id: Int): String {
        return repository.getImageName(id)
    }

    fun getImageUrl(id: Int): String {
        return repository.getImageUrl(id)
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }

    fun getImageById(id: Int): Image {
        return repository.getImageById(id)
    }

    fun updateImage(id: Int, isFlipped: Boolean, isMatched: Boolean) = viewModelScope.launch {
        repository.update(id, isFlipped, isMatched)
    }

    // check if matched
    fun isMatched(id: Int): Boolean {
        return repository.isMatched(id)
    }

    fun onCardClicked(
        selectedCards: MutableList<Pair<Int, Int>>,
        row: Int,
        col: Int,
        gridSize: Int,
        maxSelectedCards: Int,
        matchedCards: MutableList<Pair<Int, Int>>,
        imageArray: Array<Image?>,
        isCardSelected: Boolean,
        image: Image?,
        navController: NavController
    ) {
        if (selectedCards.size < maxSelectedCards && !isCardSelected) {
            ImageController(repository).updateImage(image?.id ?: 0, true, false)
            selectedCards.add(Pair(row, col))

            if (selectedCards.size == maxSelectedCards) {
                Handler(Looper.getMainLooper()).postDelayed({
                    // Check if the images match using their ID
                    if (imageArray[selectedCards[0].first * 6 + selectedCards[0].second]?.id == imageArray[selectedCards[1].first * 6 + selectedCards[1].second]?.id) {
                        Log.d("TAG", "Match!")
                        // If they match, set the images to be matched
                        selectedCards.forEach { (r, c) ->
                            ImageController(repository).updateImage(
                                imageArray[r * 6 + c]?.id ?: 0,
                                true,
                                true
                            )
                        }

                        matchedCards.addAll(selectedCards)
                        if (matchedCards.size == 12) {
                            Log.d("TAG", "Game Over!")
                            val docRef = db.collection("users").document(user?.uid.toString())
                            docRef.get()
                                .addOnSuccessListener { document ->
                                    if (document != null) {
                                        Log.d("TAG", "DocumentSnapshot data: ${document.data}")
                                        val score = document.data?.get("score").toString().toInt()
                                        db.collection("users").document(user?.uid.toString())
                                            .update("score", score + 1)
                                        navController.navigate("Home")

                                    } else {
                                        Log.d("TAG", "No such document")
                                    }
                                }
                                .addOnFailureListener { exception ->
                                    Log.d("TAG", "get failed with ", exception)
                                }
// increase by // wait for all tasks to finish and then update score


                        }


                    } else {
                        Log.d("TAG", "No Match!")
                        // If they don't match, flip the images back over
                        selectedCards.forEach { (r, c) ->
                            ImageController(repository).updateImage(
                                imageArray[r * 6 + c]?.id ?: 0,
                                false,
                                false
                            )
                        }
                    }

                    selectedCards.clear()
                }, 1500) // Adjust the delay time as needed
            }
        }
    }

    // a function that returns a 2d array of strings that have the user emails and score using db
    fun getLeaderBoard(callback: (String) -> Unit) {
        val db = Firebase.firestore
        val leaderBoard = StringBuilder()

        db.collection("users")
            .orderBy("score", com.google.firebase.firestore.Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val name = document.data["email"].toString()
                    val score = document.data["score"].toString()
                    leaderBoard.append("$score | $name\n\n")
                }
                // Pass the leaderboard data to the callback function
                callback(leaderBoard.toString())
            }
            .addOnFailureListener { exception ->
                Log.d("TAG", "Error getting documents: ", exception)
                // Handle the error here or pass an error message to the callback function
                callback("Error getting leaderboard data")
            }
    }

    fun getBackground(): String {
        val db = Firebase.firestore
        var background = ""
        db.collection("users").document(user?.uid.toString()).get()
            .addOnSuccessListener { result ->

                    val url = oesult.data["background"].tostring()
                    background = url


                }


        return background
    }


}








