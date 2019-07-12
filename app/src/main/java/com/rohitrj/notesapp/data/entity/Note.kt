package com.rohitrj.notesapp.data.entity


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    var title: String,
    var note: String
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}