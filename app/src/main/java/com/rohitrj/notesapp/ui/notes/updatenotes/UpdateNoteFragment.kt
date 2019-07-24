package com.rohitrj.notesapp.ui.notes.updatenotes

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.addCallback
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI

import com.rohitrj.notesapp.R
import com.rohitrj.notesapp.data.db.NoteDatabase
import com.rohitrj.notesapp.data.entity.Note
import com.rohitrj.notesapp.internals.utlity.toast
import com.rohitrj.notesapp.ui.basefragment.BaseFragment
import com.rohitrj.notesapp.ui.notes.addnotes.AddNoteFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.update_note_fragment.*
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class UpdateNoteFragment : BaseFragment() {

    var note: Note? = null
    private lateinit var viewModel: UpdateNoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.update_note_fragment, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(UpdateNoteViewModel::class.java)
        // TODO: Use the ViewModel
        activity!!.materialToolbar.visibility = View.VISIBLE

        arguments?.let {
            note = UpdateNoteFragmentArgs.fromBundle(it).note
            activity!!.materialToolbar.title = note?.title
            editTextDisplayNote.setText(note?.note+"\n\n\n\n\n\n\n\n\n")
            editTextDisplayNote.setSelection(1)

        }

        floatingActionButtonUpdate.setOnClickListener {
            deleteAndUpdate()
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun deleteAndUpdate() {
        launch {
            note?.let {
                NoteDatabase(activity!!).getNoteDao().deleteNote(it)

                val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
                val currentDate = sdf.format(Date())
                view?.let { activity?.hideKeyboard(it) }
                val newNote = Note(
                    activity!!.materialToolbar.title.toString(),
                    editTextDisplayNote.text.toString(),
                    currentDate )

                NoteDatabase(activity!!).getNoteDao().addNote(newNote)
                activity!!.toast("Updated")
                activity!!.onBackPressed()
             }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.delete -> {
                deleteNote()
            }
            android.R.id.home ->{
                view?.let { activity?.hideKeyboard(it) }
            }
        }

        return super.onOptionsItemSelected(item)
    }
    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
    private fun deleteNote() {
        launch {
            note?.let {
                NoteDatabase(activity!!).getNoteDao().deleteNote(it)
                activity!!.toast("Deleted")
                view?.let { activity?.hideKeyboard(it) }
                activity!!.onBackPressed()
            }
        }
    }
}

