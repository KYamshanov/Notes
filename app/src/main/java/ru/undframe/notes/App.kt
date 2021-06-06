package ru.undframe.notes

import android.app.Application
import io.reactivex.rxjava3.disposables.CompositeDisposable
import ru.undframe.notes.di.components.DaggerBaseSingletonComponent
import ru.undframe.notes.view.EditNoteActivity
import ru.undframe.notes.view.MainActivity

class App : Application() {

    private var daggerNotesRepositoryComp = DaggerBaseSingletonComponent.builder().disposables(
        CompositeDisposable()
    ).app(this).build()


    fun inject(view: MainActivity) {
        daggerNotesRepositoryComp.mainActivity().application(view).build().inject(view)
    }

    fun inject(view: EditNoteActivity) {
        daggerNotesRepositoryComp.editActivity().application(view).build().inject(view)
    }

    override fun onTerminate() {
        super.onTerminate()
    }
}