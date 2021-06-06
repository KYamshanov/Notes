package ru.undframe.notes.di.components

import dagger.BindsInstance
import dagger.Component
import dagger.Subcomponent
import ru.undframe.notes.contracts.MainContract
import ru.undframe.notes.di.modules.MainPresenterModel
import ru.undframe.notes.view.MainActivity
import javax.inject.Singleton

@Singleton
@Subcomponent(modules = [MainPresenterModel::class])
interface MainActivityComponent {

    fun inject(mainActivity: MainActivity?)


    @Subcomponent.Builder
    interface Builder {
        fun build(): MainActivityComponent

        @BindsInstance
        fun application(application: MainContract.View): Builder
    }

}