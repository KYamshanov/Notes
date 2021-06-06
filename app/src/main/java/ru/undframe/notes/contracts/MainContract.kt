package ru.undframe.notes.contracts

import ru.undframe.notes.data.Note

class MainContract  {

    interface View {
        fun showNotes(notes:Array<Note>)
        fun openEditNoteActivity(note:Note)
        fun openCreateNoteActivity()
    }

    interface Presenter{

        fun launch()
    }

}