package wonderful.com.oneminute.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import java.util.Random;

import wonderful.com.oneminute.R;

public class NewsActivity extends Activity {

    private WebView webView;
    private TextView news_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        initView();
    }

    //初始化view加载不同类型的新闻
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void initView() {
        webView = (WebView) findViewById(R.id.news_webView);
        news_title = (TextView) findViewById(R.id.news_title);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowUniversalAccessFromFileURLs(true);
        settings.setTextSize(WebSettings.TextSize.LARGER);
        int i = new Random().nextInt(3);
        switch (i) {
            case 0:
                news_title.setText("娱乐新闻");
                webView.loadUrl("file:///android_asset/gamenews/index.html");
                break;
            case 1:
                news_title.setText("体育新闻");
                webView.loadUrl("file:///android_asset/sportnews/index.html");
                break;
            case 2:
                news_title.setText("军事新闻");
                webView.loadUrl("file:///android_asset/warnews/index.html");
                break;
        }
    }

    @Override
    protected void onDestroy() {
        if (webView != null) {
            webView.destroy();
        }
        super.onDestroy();
    }
}
