package ru.undframe.notes.data

import io.reactivex.rxjava3.disposables.CompositeDisposable

class DatabaseNotesRepository(private val disposable: CompositeDisposable) : NotesRepository{


    private val notes: MutableMap<String, Note> = simpleSaveNotes(
        arrayOf(
            Note.createNot("Это зачетка? Нет! Это заметка АХАХАХА, шутка"),
            Note.createNot("Заметка #2, но после будет pfxtnrf #1"),
            Note.createNot(" this is pfvtnrf #1 без текста, но с текстом, парадокс")
        )
    )


    private fun simpleSaveNotes(notes: Array<Note>):MutableMap<String,Note> {
        val notesMap:MutableMap<String,Note> = HashMap()

        notes.forEach{
            notesMap[it.id] = it
        }

        return notesMap
    }


    override fun getNotes(): Array<Note> {
        return notes.values.toTypedArray()
    }

    override fun save(note: Note) {
        notes[note.id] = note
    }

}