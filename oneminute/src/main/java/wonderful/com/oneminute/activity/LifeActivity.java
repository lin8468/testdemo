package wonderful.com.oneminute.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import wonderful.com.oneminute.R;
import wonderful.com.oneminute.entity.Life;
import wonderful.com.oneminute.utils.Contans;
import wonderful.com.oneminute.utils.HttpUtils;
import wonderful.com.oneminute.utils.JsonUtils;

public class LifeActivity extends Activity {
    private WebView wb;
    private Handler handler = new Handler();
    private int option = 0;
    private String reqUrl, respJson;
    private Life life = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life);
        initView();
        initData(option);
    }

    /**
     * webview 请求数据显示;
     */
    private void initData(int i) {
        reqUrl = Contans.getLifeUrl(i);
        new Thread() {
            @Override
            public void run() {
                try {
                    respJson = HttpUtils.loadStringFromURL(reqUrl);
                    life = JsonUtils.getLife(respJson);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            wb.loadUrl(life.content_url);
                        }
                    });
                } catch (Exception e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "网络连接错误,请检查网络设置", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    });
                }
            }
        }.start();


    }

    /**
     * 初始化 view 1: life 的title;
     */
    private void initView() {
        wb = (WebView) findViewById(R.id.life_webview);
        wb.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return true;
            }
        });
    }

    public void next(View view) {
        ++option;
        initData(option);
    }

    @Override
    protected void onDestroy() {
        if (wb != null) {
            wb.destroy();
        }
        super.onDestroy();
    }
}
