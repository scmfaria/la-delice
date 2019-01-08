package br.com.ladelice.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;
import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.ladelice.MenuComponent;
import br.com.ladelice.R;
import br.com.ladelice.mvp.MainMVP;
import br.com.ladelice.presenter.MainPresenter;
import br.com.ladelice.view.adapter.MainAdapter;
import br.com.ladelice.view.holder.MainViewHolder;
import br.com.ladelice.view.widget.ClickListenerRecyclerItem;
import br.com.ladelice.view.widget.HorizontaLayoutView;
import br.com.ladelicedata.interfaces.OnRequestInterface;
import br.com.ladelicedomain.Constants;
import br.com.ladelicedomain.entity.MenuEntity;
import br.com.ladelicedomain.entity.ResultContentArrayEntity;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, MainMVP.ViewImpl, OnRequestInterface<ResultContentArrayEntity> {
    private RecyclerView rvMenu;
    private TextView tvSubTitle;
    private AppBarLayout appBar;
    private float setMargin, setHeight;
    private int closeApp = 1;
    private boolean disableAppBar = false;
    private NavigationView navViewMenu;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private MainAdapter adapter;
    private MainPresenter presenter;
    private ProgressDialog pdLoading;
    @Inject List<MenuEntity> menus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainPresenter();
        presenter.setView(this);
        presenter.initComponents();

        presenter.imageLoaderConfig(getApplicationContext());
        presenter.initComponentMenu();

        if(savedInstanceState != null)
            presenter.reloadPageForSaveInstanceState(menus, disableAppBar, savedInstanceState);
        else
            presenter.loadData(this);

    }

    @Override
    public void initComponents(){
        rvMenu = findViewById(R.id.rvMenu);
        tvSubTitle = findViewById(R.id.tvSubTitle);
        appBar = findViewById(R.id.appBar);
        navViewMenu = findViewById(R.id.navViewMenu);
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawerLayout);

        presenter.initScreenMargins(getResources());

        initNavViewMenu();
        initAnimationSubTitle();
        initRecyclerView();
    }

    @Override
    public void initComponentMenu(MenuComponent menuComponent){
        menus = menuComponent.provideMenu();
        menuComponent.inject(menus);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("listMenus", (Serializable) menus);
        outState.putBoolean("disableAppBar", disableAppBar);
    }

    @Override
    public Context returnContextView(){
        return getApplicationContext();
    }

    @Override
    public void initScreenMargins(float setMargin, float setHeight) {
        this.setMargin = setMargin;
        this.setHeight = setHeight;
    }

    @Override
    public void setAnimationSubTitle(int anim) {
        Animation animation = AnimationUtils.loadAnimation(MainActivity.this, anim);
        animation.setFillAfter(true);
        tvSubTitle.startAnimation(animation);
    }

    @Override
    public void loadListWithData(List<MenuEntity> menuEntities){
        menus = menuEntities;
        adapter = new MainAdapter(menus, getApplicationContext());
        rvMenu.setAdapter(adapter);

        if(menus.size() == 0)
            disableScrollAppBar();

        adjustRecyclerView();
    }

    @Override
    public void disableScrollAppBar(){
        if(appBar.getLayoutParams() != null) {
            CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) appBar.getLayoutParams();
            AppBarLayout.Behavior appBarLayoutBehaviour = new AppBarLayout.Behavior();
            appBarLayoutBehaviour.setDragCallback(new AppBarLayout.Behavior.DragCallback() {
                @Override
                public boolean canDrag(@NonNull AppBarLayout appBarLayout) {
                    return false;
                }
            });
            layoutParams.setBehavior(appBarLayoutBehaviour);
            disableAppBar = true;
        }
    }

    @Override
    public void onStartRequest() {
        pdLoading = new ProgressDialog(this);
        pdLoading.setCancelable(false);
        pdLoading.setMessage("Aguarde...");
        pdLoading.setCanceledOnTouchOutside(false);
        pdLoading.show();
    }

    @Override
    public void onResult(ResultContentArrayEntity result) {
        pdLoading.dismiss();
        presenter.loadListWithData(result.getContent());
    }

    @Override
    public void onFailure(Throwable t) {
        showToast(getResources().getString(R.string.noContent));
        pdLoading.dismiss();
        disableScrollAppBar();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void initNavViewMenu(){
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navViewMenu.setNavigationItemSelectedListener(this);
    }

    private void initAnimationSubTitle() {
        appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBar, int verticalOffset) {
                presenter.setAnimationSubTitle(appBar, verticalOffset);
            }
        });
    }

    private void initRecyclerView() {
        HorizontaLayoutView horizontaLayoutView = new HorizontaLayoutView(this);
        horizontaLayoutView.setOrientation(LinearLayoutManager.HORIZONTAL);
        horizontaLayoutView.setScrollEnabled(false);
        rvMenu.setLayoutManager(horizontaLayoutView);

        clickItemRecyclerView();
    }

    private void clickItemRecyclerView(){
        rvMenu.addOnItemTouchListener(
                new ClickListenerRecyclerItem(getApplicationContext(), rvMenu ,new ClickListenerRecyclerItem.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        if(adapter.isScrollFinished()){
                            openMenuScreen(position);
                        }
                    }
                    @Override public void onLongItemClick(View view, int position) {
                        // do nothing
                    }
                })
        );
    }

    private void openMenuScreen(int position){
        Intent intent = new Intent(MainActivity.this, MenuActivity.class);
        intent.putExtra("menu", menus.get(position));

        MainViewHolder mainViewHolder = adapter.getViewHolderAtPosition(position);

        Pair[] pairs = new Pair[3];
        pairs[0] = new Pair <View, String> (mainViewHolder.image, "item_image");
        pairs[1] = new Pair <View, String> (mainViewHolder.title, "item_title");
        pairs[2] = new Pair <View, String> (mainViewHolder.description, "item_description");

        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this, pairs);
        startActivity(intent, optionsCompat.toBundle());
    }

    private void adjustRecyclerView() {
        appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) rvMenu.getLayoutParams();
                layoutParams.topMargin = (int) ((appBarLayout.getTotalScrollRange() - Math.abs(verticalOffset)) / (double) appBarLayout.getTotalScrollRange() * setMargin);
                rvMenu.setLayoutParams(layoutParams);

                int imageHeight = (int) ((Math.abs(verticalOffset)) / (double) appBarLayout.getTotalScrollRange() * setHeight);
                ((MainAdapter) rvMenu.getAdapter()).changeImageWidth(imageHeight, imageHeight == setHeight);

                HorizontaLayoutView horizontaLayoutView = (HorizontaLayoutView) rvMenu.getLayoutManager();
                if (Math.abs(verticalOffset) - appBarLayout.getTotalScrollRange() == 0)
                    horizontaLayoutView.setScrollEnabled(true);
                else
                    horizontaLayoutView.setScrollEnabled(false);
            }
        });
    }

    private void delayCloseApp(){
        closeApp--;
        Toast.makeText(getApplicationContext(), "Aperte voltar novamente para fechar o aplicativo!", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                closeApp = 1;
            }
        }, Constants.CLOSE_APP_DELAY);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else {
            if (closeApp == 0)
                super.onBackPressed();
            else
                delayCloseApp();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    protected void onPause() {
        super.onPause();

        if(pdLoading != null)
            pdLoading.dismiss();
    }
}
