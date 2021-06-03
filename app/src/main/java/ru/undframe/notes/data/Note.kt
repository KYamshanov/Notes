package ru.undframe.notes.data

import java.util.*

data class Note(val notName:String, val text:String,val timeCreate:Date){

    companion object{

        fun createNot(text: String):Note{
            return Note(text.substring(0,22).plus("..."),text, Date())
        }

    }

}