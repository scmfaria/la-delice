package br.com.ladelice.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import br.com.ladelice.R;
import br.com.ladelicedomain.Constants;

public class SplashScreenActivity extends Activity {
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        transitionScreenDelay();
    }

    private void transitionScreenDelay(){
        handler = new Handler();

        runnable = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.animation_transition_1, R.anim.animation_transition_2);
                finish();
            }
        };

        handler.postDelayed(runnable, Constants.SPLASH_SCREEN_DELAY);
    }

    @Override
    public void onBackPressed() {
        handler.removeCallbacks(runnable);
        finish();
    }
}
