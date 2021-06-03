package ru.undframe.notes.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.undframe.notes.R
import ru.undframe.notes.data.Note

class NotesAdapter(private val notes: Array<Note>, private val action: (Note) -> Unit) :
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {


    private companion object
    class NotesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private var text: TextView = itemView.findViewById<View>(R.id.note_text) as TextView

        fun bind(note: Note, action: (Note) -> Unit) {
            text.text = note.text
            text.setOnClickListener { action.invoke(note) }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val inflate = LayoutInflater.from(parent.context)
            .inflate(R.layout.note_item_layout, parent, false)
        return NotesViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.bind(notes[position], action)
    }

    override fun getItemCount(): Int {
        return notes.size
    }


}

