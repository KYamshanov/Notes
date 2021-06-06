package ru.undframe.notes.di.components

import dagger.BindsInstance
import dagger.Subcomponent
import ru.undframe.notes.contracts.EditNoteContract
import ru.undframe.notes.di.modules.EditPresenterModel
import ru.undframe.notes.view.EditNoteActivity
import javax.inject.Singleton


@Singleton
@Subcomponent(modules = [EditPresenterModel::class])
interface EditActivityComponent {
    fun inject(mainActivity: EditNoteActivity?)


    @Subcomponent.Builder
    interface Builder {
        fun build(): EditActivityComponent

        @BindsInstance
        fun application(application: EditNoteContract.View): Builder
    }

}