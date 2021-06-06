package ru.undframe.notes.data


interface NotesRepository {
    fun getNotes(): Array<Note>
    fun save(note: Note)
}