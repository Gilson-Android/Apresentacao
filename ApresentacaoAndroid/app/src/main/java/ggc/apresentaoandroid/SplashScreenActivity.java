package ggc.apresentaoandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.transition.Slide;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;

public class SplashScreenActivity extends Activity {

    // Tempo da splash
    private static int SPLASH_TIME_OUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        setupWindowAnimations(this);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent i = new Intent(SplashScreenActivity.this, PrincipalActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.righttoleft, R.anim.splashfadeout);
                finish();


            }
        }, SPLASH_TIME_OUT);
    }

    private void setupWindowAnimations(Activity activity_) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP)
        {
            Slide slide = null;
            slide = new Slide();
            slide.setDuration(2000);
            getWindow().setExitTransition(slide);
        }

        activity_.findViewById(R.id.logo_cliente_1).startAnimation(AnimationUtils.loadAnimation(activity_, R.anim.lefttoright));
        activity_.findViewById(R.id.logo_cliente_2).startAnimation(AnimationUtils.loadAnimation(activity_,R.anim.righttoleft));
        activity_.findViewById(R.id.logo_cliente_3).startAnimation(AnimationUtils.loadAnimation(activity_, R.anim.lefttoright));
        activity_.findViewById(R.id.logo_cliente_4).startAnimation(AnimationUtils.loadAnimation(activity_, R.anim.righttoleft));
        activity_.findViewById(R.id.logo_cliente_5).startAnimation(AnimationUtils.loadAnimation(activity_, R.anim.toup));
    }
}
