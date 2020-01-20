package com.rohitrj.notesapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.rohitrj.notesapp.data.entity.Note

@Dao
interface NoteDao {

    @Insert
    suspend fun addNote(note: Note)

    // A normal query
    @Query("Select * from notes order by id desc")
    fun getAllNotes(): LiveData <List <Note> >

    @Delete
    suspend fun deleteNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)
}