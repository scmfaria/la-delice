package br.com.ladelice.model;

import javax.inject.Inject;

import br.com.ladelicedata.component.CallServiceComponent;
import br.com.ladelicedomain.entity.ResultContentArrayEntity;
import br.com.ladelicedata.interfaces.OnRequestInterface;
import br.com.ladelice.mvp.MainMVP;
import br.com.ladelicedata.service.CallService;

public class MainModel implements MainMVP.ModelImpl {

    private MainMVP.PresenterImpl presenter;
    @Inject CallService callService;

    public MainModel(MainMVP.PresenterImpl presenter) {
        this.presenter = presenter;
    }

    @Override
    public void loadData(OnRequestInterface<ResultContentArrayEntity> onRequestInterface) {
        presenter.initCallService();
        callService.listMenu(onRequestInterface);
    }

    @Override
    public void initCallService(CallServiceComponent callServiceComponent){
        callService = callServiceComponent.provideCallService();
        callServiceComponent.inject(callService);
    }
}
