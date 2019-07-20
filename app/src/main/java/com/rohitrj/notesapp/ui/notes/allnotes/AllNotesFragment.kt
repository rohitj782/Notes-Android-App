package com.rohitrj.notesapp.ui.notes.allnotes

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import kotlinx.android.synthetic.main.activity_main.*
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.rohitrj.notesapp.data.db.NoteDatabase
import com.rohitrj.notesapp.internals.adapters.NoteAdapter
import com.rohitrj.notesapp.ui.basefragment.BaseFragment
import kotlinx.android.synthetic.main.all_notes_fragment.*
import kotlinx.coroutines.launch


class AllNotesFragment : BaseFragment() {

    companion object {
        fun newInstance() = AllNotesFragment()
    }

    private lateinit var viewModel: AllNotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(com.rohitrj.notesapp.R.layout.all_notes_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AllNotesViewModel::class.java)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity!!.materialToolbar.visibility = View.GONE

        floatingActionButtonAdd.setOnClickListener {
            Navigation.findNavController(view).navigate(AllNotesFragmentDirections.addNote())
        }

    }

    override fun onResume() {
        super.onResume()
        recyclerView.setHasFixedSize(true)
        val layout = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layout
        launch {
            val notes = NoteDatabase(context!!).getNoteDao().getAllNotes()
            recyclerView.adapter = NoteAdapter(notes)
        }

    }

}
