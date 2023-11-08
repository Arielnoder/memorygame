package com.example.memorygame

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.memorygame.Controller.SettingsController
import com.example.memorygame.model.ImageRepository

@Composable
fun Background(repository: ImageRepository, navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
) {
        Box(
            modifier = Modifier
                .size(200.dp)
                .padding(20.dp)
                .clickable { SettingsController(repository).setBackground(SettingsController(repository).getBackgrounds()[0], navController)  }

        ) {
            AsyncImage(
                model = SettingsController(repository).getBackgrounds()[0],
                contentDescription = "background",
                contentScale = ContentScale.Crop
            )
        }
        Box(
            modifier = Modifier
                .size(200.dp)
                .padding(20.dp)
                .clickable { SettingsController(repository).setBackground(SettingsController(repository).getBackgrounds()[1], navController) }
        ) {
            AsyncImage(
                model = SettingsController(repository).getBackgrounds()[1],
                contentDescription = "background",
                contentScale = ContentScale.Crop,


            )
        }
        Box(
            modifier = Modifier
                .size(200.dp)
                .padding(20.dp)
                .clickable { SettingsController(repository).setBackground(SettingsController(repository).getBackgrounds()[2], navController) }

        ) {
            AsyncImage(
                model = SettingsController(repository).getBackgrounds()[2],
                contentDescription = "background",
                contentScale = ContentScale.Crop
            )
        }
    }

    }







