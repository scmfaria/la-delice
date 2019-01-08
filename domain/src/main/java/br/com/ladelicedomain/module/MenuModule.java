package br.com.ladelicedomain.module;

import java.util.ArrayList;
import java.util.List;

import br.com.ladelicedomain.entity.MenuEntity;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class MenuModule {

    @Provides
    @Singleton
    static List<MenuEntity> provideMenu() {
        List<MenuEntity> menuEntities = new ArrayList<>();

        return ( menuEntities );
    }
}

