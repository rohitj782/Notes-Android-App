package com.rohitrj.notesapp.ui.notes.updatenotes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.rohitrj.notesapp.data.db.NoteDatabase
import com.rohitrj.notesapp.data.entity.Note
import com.rohitrj.notesapp.data.repository.NotesRepository
import kotlinx.coroutines.launch

class UpdateNoteViewModel(application: Application) : AndroidViewModel(application) {
    // The ViewModel maintains a reference to the repository to get data.
    private val repository: NotesRepository

    init {
        // Gets reference to NoteDao from NoteDatabase to construct
        // the correct NoteRepository.
        val wordsDao = NoteDatabase(application).getNoteDao()
        repository = NotesRepository(wordsDao)
    }

    fun update(note: Note) = viewModelScope.launch {
        repository.update(note)
    }

    fun delete(note: Note) = viewModelScope.launch {
        repository.delete(note)
    }
}
