package ru.undframe.notes.di.modules

import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.disposables.CompositeDisposable
import ru.undframe.notes.data.DatabaseNotesRepository
import ru.undframe.notes.data.NoteDatabase
import ru.undframe.notes.data.NotesRepository
import ru.undframe.notes.di.scopes.BaseSingletonScope

@Module
class NotesRepositoryModule {
    @Provides
    @BaseSingletonScope
    fun notesRepository(
        disposable: CompositeDisposable,
        noteDatabase: NoteDatabase
    ): NotesRepository {
        val databaseNotesRepository = DatabaseNotesRepository(disposable, noteDatabase)
        databaseNotesRepository.loadNotesFromDatabase()
        return databaseNotesRepository
    }
}