package ru.undframe.notes.presentor

import ru.undframe.notes.contracts.EditNoteContract
import ru.undframe.notes.data.Note
import ru.undframe.notes.data.NotesRepository


class EditorNotePresenter(private val view: EditNoteContract.View,private val notesRepository: NotesRepository) : EditNoteContract.Presenter {


    override fun saveNote(note: Note) {
        notesRepository.save(note)
    }


}