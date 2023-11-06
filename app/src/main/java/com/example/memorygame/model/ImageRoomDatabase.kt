package com.example.memorygame.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Image::class], version = 1)
public abstract class ImageRoomDatabase : RoomDatabase() {

    abstract fun imageDao(): ImageDao

    companion object {

        @Volatile
        private var INSTANCE: ImageRoomDatabase? = null

        fun getDatabase(context: Context): ImageRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ImageRoomDatabase::class.java,
                    "image_database"
                ).allowMainThreadQueries().build()
                INSTANCE = instance
                instance
            }
        }
    }
}