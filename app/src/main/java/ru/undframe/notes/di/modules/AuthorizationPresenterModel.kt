package ru.undframe.notes.di.modules

import dagger.Module
import dagger.Provides
import ru.undframe.notes.contracts.AuthorizationContract
import ru.undframe.notes.presentor.AuthorizationPresenter
import javax.inject.Singleton

@Module
class AuthorizationPresenterModel {
    @Provides
    @Singleton
    fun getPresenter(): AuthorizationContract.Presenter {
        return AuthorizationPresenter()
    }
}