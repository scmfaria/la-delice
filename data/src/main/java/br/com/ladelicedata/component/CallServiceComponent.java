package br.com.ladelicedata.component;

import javax.inject.Singleton;

import br.com.ladelicedata.module.CallServiceModule;
import br.com.ladelicedata.service.CallService;
import dagger.Component;

@Singleton
@Component(modules = CallServiceModule.class)
public interface CallServiceComponent {

    void inject( CallService callService );

    CallService provideCallService();
}
