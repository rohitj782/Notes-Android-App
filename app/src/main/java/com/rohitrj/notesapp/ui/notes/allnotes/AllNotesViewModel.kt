package com.rohitrj.notesapp.ui.notes.allnotes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.rohitrj.notesapp.data.db.NoteDatabase
import com.rohitrj.notesapp.data.entity.Note
import com.rohitrj.notesapp.data.repository.NotesRepository

class AllNotesViewModel(application: Application) : AndroidViewModel(application) {
    // The ViewModel maintains a reference to the repository to get data.
    private val repository: NotesRepository

    val allNotes: LiveData<List<Note>>

    init {
        // Gets reference to NoteDao from NoteDatabase to construct
        // the correct NoteRepository.
        val wordsDao = NoteDatabase(application).getNoteDao()
        repository = NotesRepository(wordsDao)
        allNotes = repository.allNotes
    }
}
