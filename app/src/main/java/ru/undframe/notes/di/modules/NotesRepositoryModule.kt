package ru.undframe.notes.di.modules

import dagger.Module
import dagger.Provides
import ru.undframe.notes.data.DatabaseNotesRepository
import ru.undframe.notes.data.NotesRepository
import ru.undframe.notes.di.scopes.BaseSingletonScope

@Module
class NotesRepositoryModule {
    @Provides
    @BaseSingletonScope
    fun notesRepository(): NotesRepository {
        return DatabaseNotesRepository()
    }
}