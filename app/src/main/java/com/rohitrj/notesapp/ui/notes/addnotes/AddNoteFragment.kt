package com.rohitrj.notesapp.ui.notes.addnotes

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.rohitrj.notesapp.R
import com.rohitrj.notesapp.data.db.NoteDatabase
import com.rohitrj.notesapp.data.entity.Note
import com.rohitrj.notesapp.ui.basefragment.BaseFragment
import com.rohitrj.notesapp.internals.utlity.toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.add_note_fragment.*
import kotlinx.coroutines.launch

class AddNoteFragment : BaseFragment() {

    companion object {
        fun newInstance() = AddNoteFragment()
    }

    private lateinit var viewModel: AddNoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_note_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AddNoteViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity!!.materialToolbar.visibility = View.VISIBLE

        floatingActionButtonSave.setOnClickListener {
            val title = editTextTitle.text.toString()
            val note = editTextNote.text.toString()

            if(title.isEmpty()){
                editTextTitle.setError("Title required...")
                editTextTitle.requestFocus()
                return@setOnClickListener
            }
            if(note.isEmpty()){
                editTextNote.error = "Write something..."
                editTextNote.requestFocus()
                return@setOnClickListener
            }

            val newNote = Note(title, note)

            //launching a coroutine
            launch {
                NoteDatabase(context!!).getNoteDao().addNote(newNote)
                context!!.toast("saved")
            }
            activity!!.onBackPressed()
        }

    }

}
