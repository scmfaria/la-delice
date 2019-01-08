package br.com.ladelicedata.interfaces;

import br.com.ladelicedomain.entity.ResultContentArrayEntity;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface CallInterface {
    String BASE_URL = "http://demo6542863.mockable.io/ladelice/";

    @GET("menus")
    Observable<ResultContentArrayEntity> listMenu();
}