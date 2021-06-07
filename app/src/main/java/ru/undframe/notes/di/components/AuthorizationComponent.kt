package ru.undframe.notes.di.components

import dagger.BindsInstance
import dagger.Subcomponent
import ru.undframe.notes.contracts.AuthorizationContract
import ru.undframe.notes.di.modules.AuthorizationPresenterModel
import ru.undframe.notes.view.AuthorizationActivity
import javax.inject.Singleton

@Singleton
@Subcomponent(modules = [AuthorizationPresenterModel::class])
interface AuthorizationComponent {
    fun inject(activity: AuthorizationActivity)


    @Subcomponent.Builder
    interface Builder {
        fun build(): AuthorizationComponent

        @BindsInstance
        fun application(application: AuthorizationContract.View): Builder
    }

}
