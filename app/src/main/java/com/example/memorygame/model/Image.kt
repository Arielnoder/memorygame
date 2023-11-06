package com.example.memorygame.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "image_table")
 class Image(
@PrimaryKey(autoGenerate = true) val id: Int,
@ColumnInfo(name = "url")
val url: String,
@ColumnInfo(name = "name")
val name: String,
@ColumnInfo(name = "isFlipped")
var isFlipped: Boolean = false,
@ColumnInfo(name = "isMatched")
var isMatched: Boolean = false
)
