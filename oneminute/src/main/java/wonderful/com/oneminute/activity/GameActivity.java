package wonderful.com.oneminute.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.util.Random;

import wonderful.com.oneminute.R;

public class GameActivity extends Activity {

    private WebView mWebGame;
    private WebSettings mWebGameSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        initView();
    }

    //初始化WebView
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void initView() {
        mWebGame = (WebView) findViewById(R.id.webGame);
        mWebGame.addJavascriptInterface(new CallBack(), "callback");
        mWebGameSettings = mWebGame.getSettings();
        //支持JS
        mWebGameSettings.setJavaScriptEnabled(true);
        mWebGameSettings.setAllowUniversalAccessFromFileURLs(true);
        //允许内容访问
        mWebGameSettings.setAllowContentAccess(true);
        //启动App缓存
        //mWebGameSettings.setAppCacheEnabled(true);
        //缩放控制
        //mWebGameSettings.setBuiltInZoomControls(true);
        //设置webview推荐使用的窗口
        //mWebGameSettings.setUseWideViewPort(true);
        //加载模式
        //mWebGameSettings.setLoadWithOverviewMode(true);
        //布局算法
        //mWebGameSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        //轻触摸启动
        //mWebGameSettings.setLightTouchEnabled(true);
        //支持放大
        //mWebGameSettings.setSupportZoom(true);
        //触摸反馈
        //mWebGame.setHapticFeedbackEnabled(false);
        // 改变这个值可以设定初始大小
        //mWebGame.setInitialScale(0);
        int random = new Random().nextInt(7);
        switch (random) {
            case 0:
                mWebGame.loadUrl("file:///android_asset/huahua/huahua.html");
                break;
            case 1:
                mWebGame.loadUrl("file:///android_asset/mofang/index.html");
                break;
            case 2:
                mWebGame.loadUrl("file:///android_asset/qiqiu/index.html");
                break;
            case 3:
                mWebGame.loadUrl("file:///android_asset/shitoubu/game.html");
                break;
            case 4:
                mWebGame.loadUrl("file:///android_asset/nanshen/index.html");
                break;
            case 5:
                mWebGame.loadUrl("file:///android_asset/nvshen/index.html");
                break;
            case 6:
                mWebGame.loadUrl("file:///android_asset/tomcat/index.html");
        }
    }

    @Override
    protected void onDestroy() {
        if (mWebGame != null) {
            mWebGame.destroy();
        }
        super.onDestroy();
    }

    class CallBack {
        @JavascriptInterface
        public void jump() {
            finish();
        }
    }
}
