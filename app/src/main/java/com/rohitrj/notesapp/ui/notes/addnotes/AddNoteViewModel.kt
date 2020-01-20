package com.rohitrj.notesapp.ui.notes.addnotes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.rohitrj.notesapp.data.db.NoteDatabase
import com.rohitrj.notesapp.data.entity.Note
import com.rohitrj.notesapp.data.repository.NotesRepository
import kotlinx.coroutines.launch

class AddNoteViewModel(application: Application) : AndroidViewModel(application) {

    // The ViewModel maintains a reference to the repository to get data.
    private val repository: NotesRepository

    init {
        // Gets reference to NoteDao from NoteDatabase to construct
        // the correct NoteRepository.
        val wordsDao = NoteDatabase(application).getNoteDao()
        repository = NotesRepository(wordsDao)
    }

    fun insert(note: Note) = viewModelScope.launch {
        repository.insert(note)
    }

}
