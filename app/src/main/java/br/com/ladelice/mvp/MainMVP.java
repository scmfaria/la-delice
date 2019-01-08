package br.com.ladelice.mvp;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;

import java.util.List;

import br.com.ladelice.MenuComponent;
import br.com.ladelicedata.component.CallServiceComponent;
import br.com.ladelicedata.interfaces.OnRequestInterface;
import br.com.ladelicedomain.entity.MenuEntity;
import br.com.ladelicedomain.entity.ResultContentArrayEntity;

public interface MainMVP {

    interface ModelImpl{
        void loadData(OnRequestInterface<ResultContentArrayEntity> onRequestInterface);
        void initCallService(CallServiceComponent callServiceComponent);
    }

    interface PresenterImpl{
        void imageLoaderConfig(Context context);
        void loadData(OnRequestInterface<ResultContentArrayEntity> onRequestInterface);
        void loadListWithData(List<MenuEntity> menuEntities);
        void reloadPageForSaveInstanceState(List<MenuEntity> menuEntities, boolean disableAppBar, Bundle savedInstanceState);
        void initScreenMargins(Resources res);
        void initComponents();
        void setAnimationSubTitle(AppBarLayout appBar, int verticalOffset);
        void initComponentMenu();
        void initCallService();
        void disableScrollAppBar();
    }

    interface ViewImpl{
        void initScreenMargins(float setMargin, float setHeight);
        void initComponents();
        void loadListWithData(List<MenuEntity> menuEntities);
        void setAnimationSubTitle(int anim);
        void initComponentMenu(MenuComponent menuComponent);
        void disableScrollAppBar();
        void showToast(String message);
        Context returnContextView();
    }
}
