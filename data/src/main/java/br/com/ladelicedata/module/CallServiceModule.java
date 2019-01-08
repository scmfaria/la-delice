package br.com.ladelicedata.module;

import javax.inject.Singleton;

import br.com.ladelicedata.service.CallService;
import dagger.Module;
import dagger.Provides;

@Module
public class CallServiceModule {

    @Provides
    @Singleton
    static CallService provideCallService() {
        return ( new CallService() );
    }
}
