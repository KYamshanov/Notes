package ru.undframe.notes.contracts

import ru.undframe.notes.data.Note

class EditNoteContract {

    interface View {
    }

    interface Presenter{
        fun saveNote(note: Note)

    }
}