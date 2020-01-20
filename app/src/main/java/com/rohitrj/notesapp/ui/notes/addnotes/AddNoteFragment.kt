package com.rohitrj.notesapp.ui.notes.addnotes

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager

import com.rohitrj.notesapp.R
import com.rohitrj.notesapp.data.db.NoteDatabase
import com.rohitrj.notesapp.data.entity.Note
import com.rohitrj.notesapp.internals.utlity.basefragment.BaseFragment
import com.rohitrj.notesapp.internals.utlity.hideKeyboard
import com.rohitrj.notesapp.internals.utlity.toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.add_note_fragment.*
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class AddNoteFragment : BaseFragment() {

    private lateinit var viewModel: AddNoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.add_note_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AddNoteViewModel::class.java)
    }

    @SuppressLint("SimpleDateFormat")
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

            val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
            val currentDate = sdf.format(Date())

            val newNote = Note(title, note, currentDate)

            viewModel.insert(newNote)
            view.let { activity?.hideKeyboard(it) }
            activity!!.onBackPressed()
        }

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            android.R.id.home ->{
                view?.let { activity?.hideKeyboard(it) }
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
