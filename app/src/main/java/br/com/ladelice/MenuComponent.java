package br.com.ladelice;

import java.util.List;

import javax.inject.Singleton;

import br.com.ladelicedomain.entity.MenuEntity;
import br.com.ladelicedomain.module.MenuModule;
import dagger.Component;

@Singleton
@Component(modules = MenuModule.class)
public interface MenuComponent {

    void inject( List<MenuEntity> menuEntities );

    List<MenuEntity> provideMenu();
}
