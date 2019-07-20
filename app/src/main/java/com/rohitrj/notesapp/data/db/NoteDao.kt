package com.rohitrj.notesapp.data.db

import androidx.room.*
import com.rohitrj.notesapp.data.entity.Note

@Dao
interface NoteDao {

    @Insert
    suspend fun addNote(note: Note)

    @Query("Select * from Note order by id desc")
    suspend fun getAllNotes() : List < Note >

    @Delete
    suspend fun deleteNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)
}