package ru.undframe.notes.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.undframe.notes.R
import ru.undframe.notes.contracts.MainContract
import ru.undframe.notes.data.Note

class NotesAdapter(
    private val view: MainContract.View,
    notesArray: Array<Note>,
    private val action: (Note) -> Unit
) :
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    private val notes: MutableList<Note> = notesArray.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val inflate = LayoutInflater.from(parent.context)
            .inflate(R.layout.note_item_layout, parent, false)
        return NotesViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val note = notes[position]
        holder.bind(note, action)
        holder.deleteButton.setOnClickListener {
            view.openConfirmationWindow(note)
        }
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    fun removeNote(note: Note) {
        notes.remove(note)
    }

    fun setNotes(notesArray: Array<Note>) {
        notes.clear()
        notes.addAll(notesArray)
        notifyDataSetChanged()
    }

    private companion object
    class NotesViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {

        val text: TextView = itemView.findViewById<View>(R.id.note_text) as TextView
        val deleteButton: ImageView =
            itemView.findViewById(R.id.delete_note_button) as ImageView

        fun bind(note: Note, action: (Note) -> Unit) {
            text.text = note.name
            text.setOnClickListener { action.invoke(note) }
        }


    }

}

