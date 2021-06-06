package ru.undframe.notes.di.modules

import dagger.Module
import dagger.Provides
import ru.undframe.notes.contracts.MainContract
import ru.undframe.notes.data.NotesRepository
import ru.undframe.notes.presentor.MainPresenter
import javax.inject.Singleton

@Module
class MainPresenterModel {
    @Provides
    @Singleton
    fun getPresenter(
        view: MainContract.View?,
        notesRepository: NotesRepository?
    ): MainContract.Presenter {
        return MainPresenter(view!!, notesRepository!!)
    }
}