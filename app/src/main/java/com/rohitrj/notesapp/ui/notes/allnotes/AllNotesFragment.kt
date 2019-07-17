package com.rohitrj.notesapp.ui.notes.allnotes

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import kotlinx.android.synthetic.main.activity_main.*
import androidx.appcompat.app.ActionBar
import androidx.navigation.Navigation
import com.rohitrj.notesapp.ui.basefragment.BaseFragment
import kotlinx.android.synthetic.main.all_notes_fragment.*


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
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity!!.materialToolbar.visibility = View.GONE

        floatingActionButtonAdd.setOnClickListener {
            Navigation.findNavController(view).navigate(AllNotesFragmentDirections.nextAction())
        }

    }

}
