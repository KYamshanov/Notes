package ru.undframe.notes.data

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Note::class],version = 2,exportSchema = true)
abstract class NoteDatabase : RoomDatabase(){

    abstract fun noteDeo(): NoteDao

}