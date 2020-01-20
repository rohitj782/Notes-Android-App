package com.rohitrj.notesapp.internals.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.RecyclerView
import com.rohitrj.notesapp.R
import com.rohitrj.notesapp.data.entity.Note
import com.rohitrj.notesapp.ui.notes.allnotes.AllNotesFragmentDirections
import kotlinx.android.synthetic.main.note_display.view.*

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.MyViewHolder>() {

    private var list: List<Note> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_display,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.seeDetails(list[position])
        holder.view.setOnClickListener {
            val action = AllNotesFragmentDirections.updateNote(list[position])
            Navigation.findNavController(it).navigate(action)
        }
    }

    inner class MyViewHolder(val view: View): RecyclerView.ViewHolder(view){

        fun seeDetails(note: Note){
            view.textViewNote.text = note.note
            view.textViewTitle.text = note.title
            view.textViewDate.text = note.date

        }
    }
    fun setNotes(notes:List<Note>){
        list = notes
        notifyDataSetChanged()
    }

}