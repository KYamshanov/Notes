package ru.undframe.notes

import org.koin.core.module.Module
import org.koin.dsl.module
import ru.undframe.notes.contracts.MainContract
import ru.undframe.notes.data.NotesRepository
import ru.undframe.notes.presentor.MainPresenter

private val appModule = module {
    single {
        NotesRepository()
    }
}

val mainModule: Module = module {
    factory<MainContract.Presenter> { (view: MainContract.View) ->
        MainPresenter(view)
    }
}

object KoinModule {
    fun loadModule() = listOf(appModule,mainModule)
}