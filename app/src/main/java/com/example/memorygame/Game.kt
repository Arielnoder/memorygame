package com.example.memorygame


import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.pm.ActivityInfo
import android.os.SystemClock.sleep
import android.util.Log
import android.widget.ImageView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.memorygame.model.Image
import com.example.memorygame.model.ImageDao
import com.example.memorygame.model.ImageRepository
import com.example.memorygame.model.ImageRoomDatabase
import com.example.memorygame.model.ImageViewModel
import okhttp3.internal.wait
import java.util.concurrent.TimeUnit
var model =   "https://upload.wikimedia.org/wikipedia/commons/thumb/4/46/Question_mark_%28black%29.svg/800px-Question_mark_%28black%29.svg.png"

@Composable
    fun Game( repository: ImageRepository, navController: NavController) {

    val activity = LocalContext.current as Activity
    activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    val imageArray: Array<Image?> = arrayOfNulls<Image>(12)
    for (i in 0..5) {
        // each image should be in the array twice
        imageArray[i] = repository.getImageById(i + 1)
        imageArray[i + 6] = imageArray[i]
        Log.d("TAG", "ImageArray: ${imageArray[i]?.isFlipped}")

    }


    LazyHorizontalGrid(
        modifier = Modifier.fillMaxSize(),
        rows = GridCells.Adaptive(minSize = 120.dp), // adaptive size
        verticalArrangement = Arrangement.spacedBy(space = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(space = 16.dp),
        contentPadding = PaddingValues(all = 10.dp)
    ) {
        itemsIndexed(imageArray) { index, item ->
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(width = 100.dp),

                ) {
                if (item != null) {

                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(width = 100.dp)


                )
                Button(onClick = { /*TODO*/ }
                  {
                      AsyncImage(
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(width = 100.dp),

                          model = model,
                          contentDescription = item.name,
                          contentScale = ContentScale.Crop,

                      ))


                    }









                                }




                }


            }
        }




}





