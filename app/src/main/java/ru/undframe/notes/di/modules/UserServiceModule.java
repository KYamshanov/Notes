package ru.undframe.notes.di.modules;

import dagger.Module;
import dagger.Provides;
import ru.undframe.notes.di.scopes.BaseSingletonScope;
import ru.undframe.notes.services.UserService;

@Module
public class UserServiceModule {

    @Provides
    @BaseSingletonScope
    public UserService userService() {
        return new UserService();
    }

}
