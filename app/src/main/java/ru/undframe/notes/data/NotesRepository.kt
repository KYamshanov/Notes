package ru.undframe.notes.data


interface NotesRepository {
    fun getNotes(): Array<Note>
    fun save(note: Note,successfulAction:()->Unit)
    fun delete(note:Note,successfulAction:()->Unit)

    fun save(note: Note){
        save(note){}
    }
    fun delete(note:Note){
        delete(note){}
    }

}