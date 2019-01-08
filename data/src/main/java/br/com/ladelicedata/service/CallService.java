package br.com.ladelicedata.service;

import javax.inject.Inject;

import br.com.ladelicedomain.entity.ResultContentArrayEntity;
import br.com.ladelicedata.interfaces.CallInterface;
import br.com.ladelicedata.interfaces.OnRequestInterface;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class CallService {

    @Inject
    public CallService() {}

    @SuppressWarnings("unchecked")
    public void listMenu(final OnRequestInterface onRequestInterface){
        if (onRequestInterface != null)
            onRequestInterface.onStartRequest();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CallInterface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        CallInterface callInterface = retrofit.create(CallInterface.class);
        Observable<ResultContentArrayEntity> observable = callInterface.listMenu();

        observable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultContentArrayEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultContentArrayEntity resultContentArrayEntity) {
                        if (onRequestInterface != null)
                            onRequestInterface.onResult(resultContentArrayEntity);
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (onRequestInterface != null)
                            onRequestInterface.onFailure(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
