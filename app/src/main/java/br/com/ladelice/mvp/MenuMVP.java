package br.com.ladelice.mvp;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import br.com.ladelicedomain.entity.MenuEntity;

public interface MenuMVP {
    interface ModelImpl{}

    interface PresenterImpl{
        void loadData(Intent intent);
        void loadImage(ImageLoader imageLoader, ImageView imageView, final TextView tvProgressImage, String link);
    }

    interface ViewImpl{
        void initComponents();
        void loadData(MenuEntity menuEntity);
    }
}
