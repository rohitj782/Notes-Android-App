package com.rohitrj.notesapp.internals

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rohitrj.notesapp.R
import com.rohitrj.notesapp.data.entity.Note
import kotlinx.android.synthetic.main.note_display.view.*
import java.util.zip.Inflater

class NoteAdapter(noteList: List<Note>) : RecyclerView.Adapter<NoteAdapter.MyViewHolder>() {

    var list: List<Note> = noteList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_display,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.seeDetails(list[position])
    }

    inner class MyViewHolder(val view: View): RecyclerView.ViewHolder(view){

        fun seeDetails(note: Note){
            view.textViewNote.text = note.note
            view.textViewTitle.text = note.title
        }
    }
}