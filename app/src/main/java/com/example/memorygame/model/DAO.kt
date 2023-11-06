package com.example.memorygame.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ImageDao {
    @Query("SELECT * FROM image_table")
    fun getAll(): Flow<List<Image>>

    @Query("SELECT * FROM image_table WHERE id IN (:imageIds)")
    fun loadAllByIds(imageIds: IntArray): List<Image>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(vararg images: Image)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(image: Image)

    @Query("DELETE FROM image_table")
    suspend fun deleteAll()

    @Query("UPDATE image_table SET isFlipped = :isFlipped WHERE id = :id")
    suspend fun updateIsFlipped(id: Int, isFlipped: Boolean)

    @Query("UPDATE image_table SET isMatched = :isMatched WHERE id = :id")
    suspend fun updateIsMatched(id: Int, isMatched: Boolean)



}