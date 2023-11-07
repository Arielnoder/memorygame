package com.example.memorygame


import android.app.Activity
import android.content.pm.ActivityInfo
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.memorygame.model.Image
import com.example.memorygame.model.ImageRepository
import com.example.memorygame.model.ImageViewModel


@Composable
fun Game(repository: ImageRepository, navController: NavController, imageArray: Array<Image?>) {
    val activity = LocalContext.current as Activity
    activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE





    val gridSize = 6
    val maxSelectedCards = 2
    val selectedCards = remember { mutableStateListOf<Pair<Int, Int>>() }
    val matchedCards = remember { mutableStateListOf<Pair<Int, Int>>() }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        for (row in 0 until imageArray.size / gridSize) {
            Row {
                for (col in 0 until gridSize) {
                    val image = imageArray.getOrNull(row * 6 + col)
                    val isFlipped = image?.isFlipped ?: false
                    val isMatched = ImageViewModel(repository).isMatched(image?.id ?: 0)
                    val isCardSelected = selectedCards.contains(Pair(row, col))
                    val imageUrl = if (isCardSelected || isFlipped   ) image?.url
                        ?: "" else "https://upload.wikimedia.org/wikipedia/commons/thumb/4/46/Question_mark_%28black%29.svg/800px-Question_mark_%28black%29.svg.png"
                    if (!isMatched) {
                        Box(
                            modifier = Modifier
                                .size(100.dp)
                                .padding(10.dp)
                                .background(color = androidx.compose.material3.MaterialTheme.colorScheme.inversePrimary)
                                .clickable {
                                    if (selectedCards.size < maxSelectedCards && !isCardSelected) {
                                        ImageViewModel(repository).updateImage(image?.id ?: 0, true, false)
                                        selectedCards.add(Pair(row, col))

                                        if (selectedCards.size == maxSelectedCards) {
                                            Handler(Looper.getMainLooper()).postDelayed({
                                                // Check if the images match using their ID
                                                if (imageArray[selectedCards[0].first * 6 + selectedCards[0].second]?.id == imageArray[selectedCards[1].first * 6 + selectedCards[1].second]?.id) {
                                                    Log.d("TAG", "Match!")
                                                    // If they match, set the images to be matched
                                                    selectedCards.forEach { (r, c) ->
                                                        ImageViewModel(repository).updateImage(imageArray[r * 6 + c]?.id ?: 0, true, true)
                                                    }
                                                    matchedCards.addAll(selectedCards)
                                                } else {
                                                    Log.d("TAG", "No Match!")
                                                    // If they don't match, flip the images back over
                                                    selectedCards.forEach { (r, c) ->
                                                        ImageViewModel(repository).updateImage(imageArray[r * 6 + c]?.id ?: 0, false, false)
                                                    }
                                                }

                                                selectedCards.clear()
                                            }, 1500) // Adjust the delay time as needed
                                        }
                                    }
                                },

                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = rememberAsyncImagePainter(model = imageUrl),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                }
            }
        }

    }


}













