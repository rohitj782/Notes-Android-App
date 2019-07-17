package com.rohitrj.notesapp.data.entity


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    val title: String,
    val note: String
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}