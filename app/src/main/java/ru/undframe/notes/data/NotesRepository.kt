package ru.undframe.notes.data

class NotesRepository {

    fun getNotes(limit:Int):Array<Note>{

        return arrayOf(Note.createNot("Это зачетка? Нет! Это заметка АХАХАХА, шутка"),
        Note.createNot("Заметка #2, но после будет pfxtnrf #1"),
        Note.createNot(" this is pfvtnrf #1 без текста, но с текстом, парадокс"))

    }

}