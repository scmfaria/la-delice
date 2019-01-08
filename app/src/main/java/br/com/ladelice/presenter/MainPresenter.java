package br.com.ladelice.presenter;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.util.DisplayMetrics;

import java.util.List;

import br.com.ladelice.DaggerMenuComponent;
import br.com.ladelice.MenuComponent;
import br.com.ladelice.R;
import br.com.ladelice.model.MainModel;
import br.com.ladelice.mvp.MainMVP;
import br.com.ladelice.util.Utilities;
import br.com.ladelicedata.ConstantMethod;
import br.com.ladelicedata.component.CallServiceComponent;
import br.com.ladelicedata.component.DaggerCallServiceComponent;
import br.com.ladelicedata.interfaces.OnRequestInterface;
import br.com.ladelicedata.module.CallServiceModule;
import br.com.ladelicedomain.entity.MenuEntity;
import br.com.ladelicedomain.entity.ResultContentArrayEntity;
import br.com.ladelicedomain.module.MenuModule;

public class MainPresenter implements MainMVP.PresenterImpl {

    private MainMVP.ModelImpl model;
    private MainMVP.ViewImpl view;

    public MainPresenter(){
        this.model = new MainModel(this);
    }

    public void setView(MainMVP.ViewImpl view){
        this.view = view;
    }

    @Override
    public void imageLoaderConfig(Context context) {
        Utilities.loadImageLoaderConfigs(context);
    }

    @Override
    public void loadData(final OnRequestInterface<ResultContentArrayEntity> onRequestInterface) {
        if(ConstantMethod.verifyConnection(view.returnContextView())){
            model.loadData(new OnRequestInterface<ResultContentArrayEntity>() {
                @Override
                public void onStartRequest() {
                    onRequestInterface.onStartRequest();
                }

                @Override
                public void onResult(ResultContentArrayEntity result) {
                    onRequestInterface.onResult(result);
                }

                @Override
                public void onFailure(Throwable t) {
                    onRequestInterface.onFailure(t);
                }
            });
        }
        else{
            view.showToast(view.returnContextView().getResources().getString(R.string.noInternetConnection));
            disableScrollAppBar();
        }
    }

    @Override
    public void loadListWithData(List<MenuEntity> menuEntities) {
        view.loadListWithData(menuEntities);
    }

    @Override
    public void reloadPageForSaveInstanceState(List<MenuEntity> menuEntities, boolean disableAppBar, Bundle savedInstanceState) {
        menuEntities = (List<MenuEntity>) savedInstanceState.getSerializable("listMenus");
        disableAppBar = savedInstanceState.getBoolean("disableAppBar");

        view.loadListWithData(menuEntities);

        if(disableAppBar)
            disableScrollAppBar();
    }

    @Override
    public void initScreenMargins(Resources res) {
        DisplayMetrics metrics = res.getDisplayMetrics();
        float density = metrics.density;
        view.initScreenMargins((- density * 100), (200 * density));
    }

    @Override
    public void initComponents() {
        view.initComponents();
    }

    @Override
    public void setAnimationSubTitle(AppBarLayout appBar, int verticalOffset) {
        if (Math.abs(verticalOffset) == appBar.getTotalScrollRange()) {
            view.setAnimationSubTitle(R.anim.animation_start);
        } else if (verticalOffset == 0) {
            view.setAnimationSubTitle(R.anim.animation_center);
        }
    }

    @Override
    public void initComponentMenu() {
        MenuComponent menuComponent = DaggerMenuComponent
                .builder()
                .menuModule( new MenuModule())
                .build();

        view.initComponentMenu(menuComponent);
    }

    @Override
    public void initCallService() {
        CallServiceComponent callServiceComponent = DaggerCallServiceComponent
                .builder()
                .callServiceModule( new CallServiceModule())
                .build();

        model.initCallService(callServiceComponent);
    }

    @Override
    public void disableScrollAppBar() {
        view.disableScrollAppBar();
    }
}
