package wonderful.com.oneminute.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import org.lasque.tusdk.core.listener.AnimationListenerAdapter;

import wonderful.com.oneminute.R;

public class SplashActivity extends Activity {

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //初始化view并开始动画
        initView();
    }

    private void initView() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        ImageView view_background = (ImageView) findViewById(R.id.splash_background);
        view_background.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.main_background, options));
        ImageView view_line1 = (ImageView) findViewById(R.id.splash_one_line1);
        view_line1.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.splash_one_line1, options));
        Animation animation_line1 = AnimationUtils.loadAnimation(this, R.anim.anim_splash_line1);
        view_line1.startAnimation(animation_line1);
        ImageView view_line2 = (ImageView) findViewById(R.id.splash_one_line2);
        view_line2.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.splash_one_line2, options));
        Animation animation_line2 = AnimationUtils.loadAnimation(this, R.anim.anim_splash_line2);
        view_line2.startAnimation(animation_line2);
        View view_minute = findViewById(R.id.splash_minute);
        Animation animation_minute = AnimationUtils.loadAnimation(this, R.anim.anim_splash_minute);
        view_minute.startAnimation(animation_minute);
        final SharedPreferences sp = getSharedPreferences("isGuid", Context.MODE_PRIVATE);
        final Boolean isGuid = sp.getBoolean("isGuid", false);
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            View view_oneminute = findViewById(R.id.splash_oneminute);
                            Animation animation_oneminute = AnimationUtils.loadAnimation(getBaseContext(), R.anim.anim_splash_oneminute);
                            animation_oneminute.setAnimationListener(new AnimationListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animation animation) {
                                    if (isGuid) {
                                        startActivity(new Intent(getBaseContext(), MainActivity.class));
                                    } else {
                                        startActivity(new Intent(getBaseContext(), GuidActivity.class));
                                    }
                                    finish();
                                }
                            });
                            view_oneminute.startAnimation(animation_oneminute);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }


}
