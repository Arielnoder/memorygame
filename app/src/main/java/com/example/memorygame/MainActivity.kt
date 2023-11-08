package com.example.memorygame

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.memorygame.Controller.ImageController
import com.example.memorygame.model.Image
import com.example.memorygame.model.ImageRepository
import com.example.memorygame.model.ImageRoomDatabase
import com.google.firebase.auth.FirebaseAuth


class MainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()

        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {

                    Main()


                }
            }

        }

    }

    @SuppressLint("SuspiciousIndentation")
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Main() {
        val navController = rememberNavController()
        val database by lazy { ImageRoomDatabase.getDatabase(this) }
        val repository by lazy { ImageRepository(database.imageDao()) }
        ImageController(repository).insert(Image(1, "https://i.imgur.com/SUmPQld.jpg", "rabin", false, false,"primeMinister"))
        ImageController(repository).insert(Image(2, "https://i.imgur.com/2x9Y2Tv.jpg", "eshkol", false, false,"primeMinister"))
        ImageController(repository).insert(Image(3, "https://i.imgur.com/v6N3Fme.jpg", "begin", false, false,"primeMinister"))
        ImageController(repository).insert(Image(4, "https://i.imgur.com/izoSS0u.jpg", "davidbenguiron", false, false,"primeMinister"))
        ImageController(repository).insert(Image(5, "https://i.imgur.com/IROrCCq.jpg", "golda", false, false,"primeMinister"))
        ImageController(repository).insert(Image(6, "https://i.imgur.com/lb0vPwb.jpg", "sharat", false, false,"primeMinister"))


        val imageArray: Array<Image?> = arrayOfNulls<Image>(12)
        for (i in 0..5) {
            // each image should be in the array twice
            imageArray[i] = repository.getImageById(i + 1)
            imageArray[i + 6] = imageArray[i]
            ImageController(repository).updateImage(i+1, false, false)
            Log.d("Image", imageArray[i].toString())
        }


        NavHost(navController = navController, startDestination = Screen.LoginPage.route) {
                composable(Screen.LoginPage.route) {
                    LoginPage(navController = navController)
                }
                composable(Screen.Register.route) {
                    Register(navController = navController)
                }
                composable(Screen.Game.route) {
                    Game( repository = repository, navController = navController, imageArray = imageArray)
                }
                composable(Screen.Home.route) {
                    Home(navController = navController)
                }
                composable(Screen.LeaderBoard.route) {
                    LeaderBoard(navController = navController, repository = repository)
                }
                composable(Screen.Settings.route) {
                    Settings(navController = navController, repository = repository)
                }
                composable(Screen.Background.route) {
                    Background(navController = navController, repository = repository)
                }

                composable(Screen.Categories.route) {
                    Categories(navController = navController, repository = repository)
                }




            }
        }

    }
