package br.com.ladelice.presenter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;

import br.com.ladelicedomain.entity.MenuEntity;
import br.com.ladelice.model.MenuModel;
import br.com.ladelice.mvp.MenuMVP;
import br.com.ladelice.util.Utilities;

public class MenuPresenter implements MenuMVP.PresenterImpl {
   private MenuMVP.ModelImpl model;
   private MenuMVP.ViewImpl view;

    public MenuPresenter() {
        model = new MenuModel(this);
    }

    public void setView(MenuMVP.ViewImpl view){
        this.view = view;
    }

    @Override
    public void loadData(Intent intent) {
        MenuEntity menu = (MenuEntity) intent.getSerializableExtra("menu");
        view.loadData(menu);
    }

    @Override
    public void loadImage(ImageLoader imageLoader, ImageView imageView, final TextView tvProgressImage, String link) {
        imageLoader.displayImage(link, imageView, Utilities.getDisplayImageOptions(), new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String s, View view) {
                tvProgressImage.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingFailed(String s, View view, FailReason failReason) {
                tvProgressImage.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                tvProgressImage.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingCancelled(String s, View view) {
                tvProgressImage.setVisibility(View.GONE);
            }
        }, new ImageLoadingProgressListener() {
            @Override
            public void onProgressUpdate(String s, View view, int i, int i1) {
                int res = (i * 100) / i1;
                tvProgressImage.setText(res + "%");
            }
        });
    }
}
