package com.rohitrj.notesapp.data.entity


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "notes")
data class Note(
    @ColumnInfo (name = "title") val title: String,
    var note: String,
    var date: String
): Serializable {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}