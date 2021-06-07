package ru.undframe.notes.contracts

import ru.undframe.notes.data.Note

class MainContract  {

    interface View {
        fun showNotes(notes:Array<Note>)
        fun openEditNoteActivity(note:Note)
        fun openCreateNoteActivity()
        fun openConfirmationWindow(note: Note)
        fun refreshNotes()
        fun deleteNoteAndRefresh(note: Note)
        fun openAuthMenu()
    }

    interface Presenter{

        fun launch()
        fun deleteNote(note: Note)

    }


}