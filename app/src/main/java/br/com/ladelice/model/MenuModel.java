package br.com.ladelice.model;

import br.com.ladelice.mvp.MenuMVP;

public class MenuModel implements MenuMVP.ModelImpl {
    private MenuMVP.PresenterImpl presenter;

    public MenuModel(MenuMVP.PresenterImpl presenter) {
        this.presenter = presenter;
    }
}
