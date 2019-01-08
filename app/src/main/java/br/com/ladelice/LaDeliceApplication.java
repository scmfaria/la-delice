package br.com.ladelice;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import br.com.ladelicedata.component.CallServiceComponent;
import br.com.ladelicedata.component.DaggerCallServiceComponent;
import br.com.ladelicedata.module.CallServiceModule;
import br.com.ladelicedomain.module.MenuModule;

public class LaDeliceApplication extends MultiDexApplication {
    private CallServiceComponent mCallServiceComponent;
    private MenuComponent mMenuComponent;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);

        mCallServiceComponent = createCallService();
        mMenuComponent = createMenuComponent();
    }

    public CallServiceComponent getCallServiceComponent() {
        return mCallServiceComponent;
    }

    public MenuComponent getmMenuComponent() {
        return mMenuComponent;
    }

    private MenuComponent createMenuComponent() {
        return DaggerMenuComponent
                .builder()
                .menuModule(new MenuModule())
                .build();
    }

    private CallServiceComponent createCallService() {
        return DaggerCallServiceComponent
                .builder()
                .callServiceModule(new CallServiceModule())
                .build();
    }

}
