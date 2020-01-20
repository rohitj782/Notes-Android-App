package com.rohitrj.notesapp.data.repository

import androidx.lifecycle.LiveData
import com.rohitrj.notesapp.data.db.NoteDao
import com.rohitrj.notesapp.data.entity.Note

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class NotesRepository(private val notesDao: NoteDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allNotes: LiveData<List<Note>> = notesDao.getAllNotes()

    suspend fun insert(note: Note) {
        notesDao.addNote(note)
    }
    suspend fun update(note: Note){
        notesDao.updateNote(note)
    }
    suspend fun delete(note: Note){
        notesDao.deleteNote(note)
    }
}