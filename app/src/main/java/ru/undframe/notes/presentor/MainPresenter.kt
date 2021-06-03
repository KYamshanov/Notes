package ru.undframe.notes.presentor

import org.koin.core.KoinComponent
import org.koin.core.inject
import ru.undframe.notes.contracts.MainContract
import ru.undframe.notes.data.NotesRepository

class MainPresenter(private val mainView: MainContract.View) : MainContract.Presenter,
    KoinComponent {

    private val notesRepository: NotesRepository by inject()


    override fun launch() {
        mainView.showNotes(notesRepository.getNotes(10))
    }

}