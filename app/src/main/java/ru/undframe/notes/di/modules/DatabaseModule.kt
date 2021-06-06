package ru.undframe.notes.di.modules

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.undframe.notes.data.NoteDatabase
import ru.undframe.notes.di.scopes.BaseSingletonScope

@Module
class DatabaseModule {
    @Provides
    @BaseSingletonScope
    fun noteDatabase(application: Application?): NoteDatabase {
        return Room.databaseBuilder(application!!, NoteDatabase::class.java, "note_database")
            .fallbackToDestructiveMigration()
            .build()
    }
}