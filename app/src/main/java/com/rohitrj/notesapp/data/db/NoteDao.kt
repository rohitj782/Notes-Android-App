package com.rohitrj.notesapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.rohitrj.notesapp.data.entity.Note

@Dao
interface NoteDao {

    @Insert
    suspend fun addNote(note: Note)

    @Query("Select * from Note")
    suspend fun getAllNotes() : List < Note >

}