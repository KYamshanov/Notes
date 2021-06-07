package ru.undframe.notes.di.components

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.Subcomponent
import io.reactivex.rxjava3.disposables.CompositeDisposable
import ru.undframe.notes.contracts.MainContract
import ru.undframe.notes.di.modules.*
import ru.undframe.notes.di.scopes.BaseSingletonScope

@BaseSingletonScope
@Component(modules = [UserServiceModule::class,NotesRepositoryModule::class,DatabaseModule::class,RetrofitModule::class,IdentifierDeviceModule::class])
interface BaseSingletonComponent{

    fun editActivity():EditActivityComponent.Builder
    fun mainActivity():MainActivityComponent.Builder
    fun authActivity():AuthorizationComponent.Builder

    @Component.Builder
    interface Builder {
        fun build(): BaseSingletonComponent

        @BindsInstance
        fun app(application: Application): Builder

        @BindsInstance
        fun disposables(disposables: CompositeDisposable): Builder
    }

}