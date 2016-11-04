package wonderful.com.oneminute.activity;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import wonderful.com.oneminute.R;

/**
 * Created by Super-me on 2016/10/6.
 */

public class MusicActivity extends Activity {

    private WebView musicView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivity_music);
        initView();
    }

    private void initView() {
        musicView = (WebView) findViewById(R.id.Music_WebView);
        musicView.setWebViewClient(new WebViewClient());
        WebSettings mWebGameSettings = musicView.getSettings();
        //支持JS
        mWebGameSettings.setJavaScriptEnabled(true);
        //允许内容访问
        //mWebGameSettings.setAllowContentAccess(true);
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
        musicView.loadUrl("http://fm.baidu.com/");


    }

    @Override
    protected void onDestroy() {
        if (musicView != null) {
            musicView.destroy();
        }
        super.onDestroy();
    }
}
