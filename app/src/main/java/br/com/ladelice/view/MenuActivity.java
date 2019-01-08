package br.com.ladelice.view;

import android.app.SharedElementCallback;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.List;

import br.com.ladelice.R;
import br.com.ladelicedomain.entity.ImageEntity;
import br.com.ladelicedomain.entity.MenuEntity;
import br.com.ladelice.mvp.MenuMVP;
import br.com.ladelice.presenter.MenuPresenter;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MenuActivity extends AppCompatActivity implements MenuMVP.ViewImpl {
    private CarouselView carouselView;
    private ImageView ivClose, ivDescription;
    private TextView tvTitle, tvDescription, tvProgressLoadImage, tvProgressLoadImageDescription;
    private MenuEntity menu;
    private ImageLoader imageLoader;
    private MenuPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        initComponents();
        presenter.loadData(getIntent());
    }

    @Override
    public void initComponents(){
        carouselView = findViewById(R.id.carouselView);
        ivClose = findViewById(R.id.ivClose);
        tvTitle = findViewById(R.id.tvTitle);
        tvDescription = findViewById(R.id.tvDescription);
        ivDescription = findViewById(R.id.ivDescription);
        tvProgressLoadImage = findViewById(R.id.tvProgressLoadImage);
        tvProgressLoadImageDescription = findViewById(R.id.tvProgressLoadImageDescription);

        presenter = new MenuPresenter();
        presenter.setView(this);

        imageLoader = ImageLoader.getInstance();

        initCloseButton();
    }

    private void initCloseButton(){
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation_fadeout);
                ivClose.setAnimation(startAnimation);
                ActivityCompat.finishAfterTransition(MenuActivity.this);
            }
        });

        setAnimationCloseCallback(ivClose);
    }

    @Override
    public void loadData(MenuEntity menuEntity){
        menu = menuEntity;

        tvDescription.setText(menu.getDescriptionMenu());
        tvTitle.setText(menu.getTitleMenu());
        initCarouselView(menu.getImageEntities());

        if(menu.getImageDescription() != null){
            presenter.loadImage(imageLoader, ivDescription, tvProgressLoadImageDescription, menu.getImageDescription());
        } else
            ivDescription.setVisibility(View.GONE);

    }

    private void initCarouselView(final List<ImageEntity> imageEntities){
        carouselView.setPageCount(imageEntities.size());

        ImageListener imageListener = new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                presenter.loadImage(imageLoader, imageView, tvProgressLoadImage, imageEntities.get(position).getLink());
            }
        };

        carouselView.setImageListener(imageListener);
    }

    private void setAnimationCloseCallback(final ImageView imageView) {
        setEnterSharedElementCallback(new SharedElementCallback() {
            @Override
            public void onSharedElementEnd(List<String> sharedElementNames,
                                           List<View> sharedElements,
                                           List<View> sharedElementSnapshots) {

                Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation_fadein);
                imageView.setAnimation(startAnimation);
            }
        });
    }
}
