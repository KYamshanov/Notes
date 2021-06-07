package ru.undframe.notes.di.modules

import dagger.Module
import dagger.Provides
import ru.undframe.notes.contracts.AuthorizationContract
import ru.undframe.notes.presentor.AuthorizationPresenter
import ru.undframe.notes.services.AuthService
import ru.undframe.notes.services.IdentifierDeviceService
import ru.undframe.notes.services.UserService
import javax.inject.Singleton

@Module
class AuthorizationPresenterModel {
    @Provides
    @Singleton
    fun getPresenter(
        view: AuthorizationContract.View,
        identifierDeviceService: IdentifierDeviceService,
        authService: AuthService,
        userService: UserService
    ): AuthorizationContract.Presenter {
        return AuthorizationPresenter(view, identifierDeviceService, authService, userService)
    }
}