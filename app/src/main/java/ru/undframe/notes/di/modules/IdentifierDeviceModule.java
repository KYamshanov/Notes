package ru.undframe.notes.di.modules;

import android.app.Application;

import dagger.Module;
import dagger.Provides;
import ru.undframe.notes.di.scopes.BaseSingletonScope;
import ru.undframe.notes.services.FingerprintService;
import ru.undframe.notes.services.IdentifierDeviceService;

@Module
public class IdentifierDeviceModule {

    @BaseSingletonScope
    @Provides
    public IdentifierDeviceService getService(Application app){
        return new FingerprintService(app);
    }

}
