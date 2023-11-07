package com.example.memorygame.model

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class ImageRepository(private val imageDao: ImageDao) {

    val allImages: Flow<List<Image>> = imageDao.getAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(image: Image) {
        imageDao.insert(image)
    }

    // create a function that returns all images using getAll from the dao

    // create a function that inserts an image using insert from the dao

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    fun getImages() : Flow<List<Image>>{
        return allImages
    }



    // get image name by id
    @Suppress("RedundantSuspendModifier")

    @WorkerThread
    fun getImageName(id: Int) : String{
        return imageDao.loadAllByIds(intArrayOf(id))[0].name
    }

    // get image url by id
    @Suppress("RedundantSuspendModifier")

    @WorkerThread

    fun getImageUrl(id: Int) : String{
        return imageDao.loadAllByIds(intArrayOf(id))[0].url
    }

    // delete all images

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteAll() {
        imageDao.deleteAll()
    }

    // get image by id

    @Suppress("RedundantSuspendModifier")

    @WorkerThread

    fun getImageById(id: Int) : Image{
        return imageDao.loadAllByIds(intArrayOf(id))[0]
    }

    // update image

    @Suppress("RedundantSuspendModifier")

    @WorkerThread

    suspend fun update(id: Int, isFlipped: Boolean, isMatched: Boolean) {
        imageDao.update(id, isFlipped, isMatched)
    }

    // check if its matched

    @Suppress("RedundantSuspendModifier")

    @WorkerThread

    fun isMatched(id: Int) : Boolean{
        return imageDao.loadAllByIds(intArrayOf(id))[0].isMatched
    }





}