package com.rohitrj.notesapp.ui.notes.updatenotes

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import com.rohitrj.notesapp.R
import com.rohitrj.notesapp.data.db.NoteDatabase
import com.rohitrj.notesapp.data.entity.Note
import com.rohitrj.notesapp.internals.utlity.toast
import com.rohitrj.notesapp.internals.utlity.basefragment.BaseFragment
import com.rohitrj.notesapp.internals.utlity.hideKeyboard
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
            editTextDisplayNote.setText(note?.note)
            editTextDisplayNote.setSelection(note?.note!!.length)

        }

        floatingActionButtonUpdate.setOnClickListener {
            update()
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun update() {
        launch {
            note?.let { it ->

                val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
                val currentDate = sdf.format(Date())
                view?.let { activity?.hideKeyboard(it) }
                it.note = editTextDisplayNote.text.toString()
                it.date = currentDate

                viewModel.update(it)
                activity!!.toast("Updated")
                activity!!.onBackPressed()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.delete -> {
                deleteNote()
            }
            android.R.id.home -> {
                view?.let { activity?.hideKeyboard(it) }
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun deleteNote() {
        launch {
            note?.let { it ->
                viewModel.delete(it)
                activity!!.toast("Deleted")
                view?.let { activity?.hideKeyboard(it) }
                activity!!.onBackPressed()
            }
        }
    }
}

