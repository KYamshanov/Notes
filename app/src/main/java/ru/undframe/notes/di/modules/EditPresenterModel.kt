package ru.undframe.notes.di.modules

import dagger.Module
import dagger.Provides
import ru.undframe.notes.contracts.EditNoteContract
import ru.undframe.notes.data.NotesRepository
import ru.undframe.notes.presentor.EditorNotePresenter
import javax.inject.Singleton

@Module
class EditPresenterModel {
    @Provides
    @Singleton
    fun getPresenter(
        notesRepository: NotesRepository?
    ): EditNoteContract.Presenter {
        return EditorNotePresenter(notesRepository!!)
    }
}