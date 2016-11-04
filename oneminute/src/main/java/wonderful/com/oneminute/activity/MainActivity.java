package wonderful.com.oneminute.activity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import wonderful.com.oneminute.R;

public class MainActivity extends Activity {

    private List<String> total;
    private List<String> temp;
    private List<TextView> textViewList;
    private Map<Animator, TextView> map;
    private FrameLayout mFrameLayout;
    private DrawerLayout mDrawerLayout;
    private ImageView view_background;
    private TextView mode;
    private BitmapFactory.Options options;
    private ImageView view_drawerLayout_logo;
    private ImageView drawerBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initData() {
        total = new ArrayList<>();
        total.add("每日一笑");
        total.add("音乐随身听");
        total.add("英语美文");
        total.add("新闻快讯");
        total.add("恶搞自拍");
        total.add("我想对我说");
        total.add("创意生活");
        total.add("小游戏");
        total.add("亲情问候");
        total.add("天气推送");
        total.add("每日一句");
        temp = new ArrayList<>();
        textViewList = new ArrayList<>();
        map = new HashMap<>();
    }

    //初始化布局view
    private void initView() {
        options = new BitmapFactory.Options();
        options.inSampleSize = 3;
        view_background = (ImageView) findViewById(R.id.main_background);
        view_background.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.main_background, options));
        view_drawerLayout_logo = (ImageView) findViewById(R.id.logo);
        mFrameLayout = (FrameLayout) findViewById(R.id.rightDrawer);
        //设置侧拉菜单背景压缩图片
        drawerBackground = (ImageView) findViewById(R.id.drawerBackground);
        drawerBackground.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.drawerlayout_background, options));
        mode = (TextView) findViewById(R.id.nightModeText);
        TextView textView = (TextView) findViewById(R.id.main_textView_1);
        textViewList.add(textView);
        textView = (TextView) findViewById(R.id.main_textView_2);
        textViewList.add(textView);
        textView = (TextView) findViewById(R.id.main_textView_3);
        textViewList.add(textView);
        textView = (TextView) findViewById(R.id.main_textView_4);
        textViewList.add(textView);
        textView = (TextView) findViewById(R.id.main_textView_5);
        textViewList.add(textView);
        textView = (TextView) findViewById(R.id.main_textView_6);
        textViewList.add(textView);
        textView = (TextView) findViewById(R.id.main_textView_7);
        textViewList.add(textView);
        textView = (TextView) findViewById(R.id.main_textView_8);
        textViewList.add(textView);
        textView = (TextView) findViewById(R.id.main_textView_9);
        textViewList.add(textView);
        textView = (TextView) findViewById(R.id.main_textView_10);
        textViewList.add(textView);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        for (TextView textView_temp : textViewList) {
            setAnimation(textView_temp);
        }

    }

    //为textView设置动画
    private void setAnimation(TextView textView) {
        Animator animator = getAnimator();
        animator.setTarget(textView);
        textView.setText(getTextViewName());
        map.put(animator, textView);
        animator.start();
    }

    private String getTextViewName() {
        String name = null;
        do {
            int i = new Random().nextInt(total.size());
            name = total.get(i);
        } while (temp.contains(name));
        temp.add(name);
        return name;
    }

    //随机产生动画
    private Animator getAnimator() {
        int i = new Random().nextInt(6);
        Animator animator = null;
        switch (i) {
            case 0:
                animator = AnimatorInflater.loadAnimator(this, R.animator.anim_one);
                break;
            case 1:
                animator = AnimatorInflater.loadAnimator(this, R.animator.anim_two);
                break;
            case 2:
                animator = AnimatorInflater.loadAnimator(this, R.animator.anim_three);
                break;
            case 3:
                animator = AnimatorInflater.loadAnimator(this, R.animator.anim_four);
                break;
            case 4:
                animator = AnimatorInflater.loadAnimator(this, R.animator.anim_five);
                break;
            case 5:
                animator = AnimatorInflater.loadAnimator(this, R.animator.anim_six);
                break;
        }
        animator.addListener(new MyAnimatorListener());
        return animator;
    }


    //textView点击方法
    public void click(View view) {
        Intent it = null;
        TextView textView = (TextView) view;
        switch (textView.getText().toString()) {
            case "每日一笑":
                it = new Intent(this, JokeActivity.class);
                break;
            case "音乐随身听":
                it = new Intent(this, MusicActivity.class);
                break;
            case "英语美文":
                it = new Intent(this, Daily_EnglishActivity.class);
                break;
            case "新闻快讯":
                it = new Intent(this, NewsActivity.class);
                break;
            case "恶搞自拍":
                it = new Intent(this, PhotoGraphActivity.class);
                break;
            case "我想对我说":
                it = new Intent(this, RecordActivity.class);
                break;
            case "创意生活":
                it = new Intent(this, LifeActivity.class);
                break;
            case "小游戏":
                it = new Intent(this, GameActivity.class);
                break;
            case "亲情问候":
                it = new Intent(this, CityActivity.class);
                break;
            case "天气推送":
                it = new Intent(this, WeatherActivity.class);
                break;
            case "每日一句":
                it = new Intent(this, OneActivity.class);
                break;
        }
        startActivity(it);
    }

    //设置按钮，点击打开侧拉菜单
    public void openDrawerLayout(View view) {
        if (!mDrawerLayout.isDrawerOpen(mFrameLayout)) {
            mDrawerLayout.openDrawer(mFrameLayout);
        }
    }

    //侧拉菜单中关于我们的点击响应方法
    public void AboutUs(View view) {
        Intent intent = new Intent();
        intent.setClass(getBaseContext(), AboutUsActivity.class);
        startActivity(intent);
        if (mDrawerLayout.isDrawerOpen(mFrameLayout)) {
            mDrawerLayout.closeDrawer(mFrameLayout);
        }
    }

    //侧拉菜单中夜间模式按钮
    public void NightMode(View view) {
        if (mode.getText().toString().equals("夜间模式")) {
            view_background.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.main_night_background));
            drawerBackground.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.drawerlayout_background_night, options));
            view_drawerLayout_logo.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.drawerlayout_logo_night));
            mode.setText("日常模式");
            if (mDrawerLayout.isDrawerOpen(mFrameLayout)) {
                mDrawerLayout.closeDrawer(mFrameLayout);
            }
        } else {
            view_background.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.main_background, options));
            drawerBackground.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.drawerlayout_background, options));
            view_drawerLayout_logo.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.drawerlayout_logo));
            mode.setText("夜间模式");
            if (mDrawerLayout.isDrawerOpen(mFrameLayout)) {
                mDrawerLayout.closeDrawer(mFrameLayout);
            }
        }

    }

    //侧拉菜单中您的意见按钮
    public void Suggest(View view) {
        Intent intent = new Intent();
        intent.setClass(getBaseContext(), SuggestActivity.class);
        startActivity(intent);
        if (mDrawerLayout.isDrawerOpen(mFrameLayout)) {
            mDrawerLayout.closeDrawer(mFrameLayout);
        }
    }

    //侧拉菜单中分享我们按钮
    public void Share(View view) {
        SHARE_MEDIA[] share_media = new SHARE_MEDIA[]{
                SHARE_MEDIA.QZONE, SHARE_MEDIA.SINA, SHARE_MEDIA.INSTAGRAM,
                SHARE_MEDIA.DOUBAN, SHARE_MEDIA.FACEBOOK, SHARE_MEDIA.GOOGLEPLUS, SHARE_MEDIA.LINE,
                SHARE_MEDIA.WEIXIN
        };
        //分享对象
        ShareAction shareAction = new ShareAction(this);
        //显示分享列表
        shareAction.setDisplayList(share_media);
        //分享的内容
        shareAction.withText("千里寻他众百度，锋自苦寒磨砺出");
        //分享的链接
        shareAction.withTargetUrl("http://www.1000phone.com/");
        //开始分享
        shareAction.open();
        if (mDrawerLayout.isDrawerOpen(mFrameLayout)) {
            mDrawerLayout.closeDrawer(mFrameLayout);
        }
    }

    public void start(View view) {
        //点击按钮播放帧动画
        ImageButton imageButton = (ImageButton) view;
        imageButton.setImageResource(R.drawable.start);
        AnimationDrawable drawable = (AnimationDrawable) imageButton.getDrawable();
        drawable.start();
        Timer timer = new Timer();
        //开始定时器
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        startRandom();
                    }
                });
            }
        }, 1000);

    }

    //随机跳转现有的activity功能
    private void startRandom() {
        int i = new Random().nextInt(12);
        Intent it = null;
        switch (i) {
            case 0:
                it = new Intent(this, JokeActivity.class);
                break;
            case 1:
                it = new Intent(this, MusicActivity.class);
                break;
            case 2:
                it = new Intent(this, Daily_EnglishActivity.class);
                break;
            case 3:
                it = new Intent(this, NewsActivity.class);
                break;
            case 4:
                it = new Intent(this, PhotoGraphActivity.class);
                break;
            case 5:
                it = new Intent(this, PhotoGraphActivity.class);
                break;
            case 6:
                it = new Intent(this, RecordActivity.class);
                break;
            case 7:
                it = new Intent(this, LifeActivity.class);
                break;
            case 8:
                it = new Intent(this, GameActivity.class);
                break;
            case 9:
                it = new Intent(this, CityActivity.class);
                break;
            case 10:
                it = new Intent(this, WeatherActivity.class);
                break;
            case 11:
                it = new Intent(this, OneActivity.class);
                break;
        }
        startActivity(it);
    }


    //动画结束监听接口
    class MyAnimatorListener implements Animator.AnimatorListener {

        @Override
        public void onAnimationStart(Animator animation) {

        }

        @Override
        public void onAnimationEnd(Animator animation) {
            TextView textView = map.get(animation);
            String name = textView.getText().toString();
            setAnimation(textView);
            temp.remove(name);
            map.remove(animation);
            animation = null;
        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    }

}
