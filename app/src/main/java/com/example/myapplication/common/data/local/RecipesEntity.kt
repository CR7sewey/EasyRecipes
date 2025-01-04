package com.example.myapplication.common.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RecipesEntity(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name= "title") val title: String,
    @ColumnInfo(name= "image") val image: String,
    @ColumnInfo(name= "servings") val servings: String,
    @ColumnInfo(name= "readyInMinutes") val readyInMinutes: String,
    @ColumnInfo(name= "summary") val summary: String,
)