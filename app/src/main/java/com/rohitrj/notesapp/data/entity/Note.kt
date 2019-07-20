package com.rohitrj.notesapp.data.entity


import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Note(
    val title: String,
    val note: String
): Serializable {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}