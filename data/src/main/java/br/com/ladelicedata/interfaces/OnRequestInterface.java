package br.com.ladelicedata.interfaces;

public interface OnRequestInterface<T> {
    void onStartRequest();
    void onResult(T result);
    void onFailure(Throwable t);
}
