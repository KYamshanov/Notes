package ru.undframe.notes.presentor

import ru.undframe.notes.contracts.MainContract
import ru.undframe.notes.data.NotesRepository

class MainPresenter(
    private val mainView: MainContract.View,
    private val notesRepository: NotesRepository
) : MainContract.Presenter {

    override fun launch() {
        mainView.showNotes(notesRepository.getNotes())
    }


}