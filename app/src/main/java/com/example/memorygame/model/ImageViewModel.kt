package com.example.memorygame.model


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ImageViewModel(private val repository: ImageRepository) : ViewModel() {

        val allImages: LiveData<List<Image>> = repository.getImages().asLiveData()

         fun insert(image: Image) = viewModelScope.launch {
             repository.insert(image)
         }

            fun getImages() : LiveData<List<Image>>{
                return allImages
            }
    fun getImageName(id: Int) : String{
        return repository.getImageName(id)
    }
    fun getImageUrl(id: Int) : String{
        return repository.getImageUrl(id)
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }

   fun getImageById(id: Int) : Image{
       return  repository.getImageById(id)
   }
    fun updateImage(id: Int, isFlipped: Boolean) = viewModelScope.launch {
        repository.update(id, isFlipped)
    }
}

class ImageViewModelFactory(private val repository: ImageRepository) : ViewModel() {
fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ImageViewModel::class.java)) {
        @Suppress("UNCHECKED_CAST")
        return ImageViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
        }
}
