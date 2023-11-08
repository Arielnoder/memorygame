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

class SettingsController(private val repository: ImageRepository) : ViewModel() {

    fun getBackgrounds() : Array<String> {
        return arrayOf("https://i.imgur.com/m1NSWdL.png", "https://i.imgur.com/Ym22sYz.jpg", "https://i.imgur.com/BHZGoKw.png")
    }

    fun setBackground(url: String, navController: NavController) {
        val user = FirebaseAuth.getInstance().currentUser
        val db = Firebase.firestore
        val docRef = db.collection("users").document(user?.uid.toString())
        docRef
            .update("background", url)
            .addOnSuccessListener { Log.d("TAG", "DocumentSnapshot successfully updated!") }
            .addOnFailureListener { e -> Log.w("TAG", "Error updating document", e) }
        navController.navigate("Home")

    }




}